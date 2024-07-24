package com.product.phoneshop.service;

import com.product.phoneshop.model.Brand;
import com.product.phoneshop.repository.BrandRepository;
import com.product.phoneshop.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

public class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;
    private BrandService brandService;

    @Captor
    private ArgumentCaptor<Brand> brandCaptor;
    private Brand brand;

    @BeforeEach
    public void setUp() {
        brandService = new BrandServiceImpl(brandRepository);
        brand = new Brand(1L,"Apple", true);
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
    }
    @Test
    public void testSaveBrand(){
//         given:
//        Brand brand  = new Brand();
//        brand.setName("Apple");
//         when:

        // then:
        Brand brandReturn = brandService.save(brand);
        verify(brandRepository,times(1)).save(brand);
//        assertEquals("Apple", brandReturn.getName());
//        assertEquals(1, brandReturn.getId());
    }


    @Test
    public void testGetById(){
         Brand brandReturn = brandService.getById(1L);
         verify(brandRepository,times(1)).findById(1L);
    }

    @Test
    public void testUpdateBrand(){
        Brand brandUpdate = new Brand(1L,"Apple V2", true);
        Brand brandAfterUpdate =  brandService.update(1L,brandUpdate);

       // assertEquals(brandAfterUpdate.getName(), "Apple V2");
        verify(brandRepository,times(1)).save(brandUpdate);
        //TODO check brandAfterUpdate.getName() if it equal to "Apple V2"
    }
    @Test
    public void testDeleteBrand(){
        Long brandToDelete = 1L;

        brandService.delete(brandToDelete);
        //verify(brandRepository,times(1)).delete(brandFromDB);
        verify(brandRepository).save(brandCaptor.capture());
        assertEquals(false,brandCaptor.getValue().getActive());
        verify(brandRepository,times(1)).save(brand);
    }

    @Test
    public void testListBrands(){
        List<Brand> brands = List.of(
                new Brand(1L,"Apple",true),
                new Brand(2L,"Samsung",true)
        );
        when(brandRepository.findByActiveTrue()).thenReturn(brands);
        List<Brand> brandsReturn = brandService.getAllBrand();

        assertEquals(2,brandsReturn.size());
        assertEquals("Apple",brandsReturn.get(0).getName());
        assertEquals("Samsung",brandsReturn.get(1).getName());
    }
}