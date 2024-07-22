package com.product.phoneshop.service;

import com.product.phoneshop.model.Brand;
import com.product.phoneshop.repository.BrandRepository;
import com.product.phoneshop.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    private Brand brand;

    @BeforeEach
    public void setUp() {
        brandService = new BrandServiceImpl(brandRepository);
        brand = new Brand(1,"Apple");
        when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
    }

    @Test
    public void testSaveBrand(){
//         given:
//        Brand brand  = new Brand();
//        brand.setName("Apple");
//         when:
        /*
        when(brandRepository.save(any(Brand.class))).thenAnswer(new Answer<Brand>() {

            @Override
            public Brand answer(InvocationOnMock invocationOnMock) throws Throwable {
                Brand brandEntity = invocationOnMock.getArgument(0);
                brandEntity.setId(1);
                return brandEntity;
            }
        });
        //
        when(brandRepository.save(any(Brand.class))).thenAnswer(invocationOnMock -> {
            Brand brandEntity = invocationOnMock.getArgument(0);
            brandEntity.setId(1);
            return brandEntity;
        });
         */
        // then:
        Brand brandReturn = brandService.save(brand);
       // verify(brandRepository,times(1)).save(brand);
//        assertEquals("Apple", brandReturn.getName());
//        assertEquals(1, brandReturn.getId());
    }


    @Test
    public void testGetById(){


         Brand brandReturn = brandService.getById(1);
         verify(brandRepository,times(1)).findById(1);
    }

    @Test
    public void testUpdateBrand(){
        Brand brandUpdate = new Brand(1,"Apple V2");
        Brand brandAfterUpdate =  brandService.update(1,brandUpdate);

       // assertEquals(brandAfterUpdate.getName(), "Apple V2");
        verify(brandRepository,times(1)).save(brandUpdate);
        //TODO check brandAfterUpdate.getName() if it equal to "Apple V2"
    }
    @Test
    public void testDeleteBrand(){
        Brand brandFromDB = new Brand(1,"Apple");
        Brand brandDelete = new Brand(1,"Apple V2");

        brandService.delete(1);
        verify(brandRepository,times(1)).delete(brandFromDB);
    }

    @Test
    public void testGetListBrands(){
        List<Brand> brands = List.of(
                new Brand(1,"Apple"),
                new Brand(2,"Samsung")
        );
        when(brandRepository.findAll()).thenReturn(brands);
        List<Brand> brandsReturn = brandService.getAllBrand();

        assertEquals(2,brandsReturn.size());
        assertEquals("Apple",brandsReturn.get(0).getName());
        assertEquals("Samsung",brandsReturn.get(1).getName());

    }
}
