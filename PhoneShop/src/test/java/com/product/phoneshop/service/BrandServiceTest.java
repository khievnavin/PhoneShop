package com.product.phoneshop.service;

import com.product.phoneshop.model.Brand;
import com.product.phoneshop.repository.BrandRepository;
import com.product.phoneshop.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    private BrandService brandService;

    @BeforeEach
    public void setUp() {
        brandService = new BrandServiceImpl(brandRepository);
    }

    @Test
    public void testSaveBrand(){
        // given:
        Brand brand  = new Brand();
        brand.setName("Apple");
        // when:
        when(brandRepository.save(any(Brand.class))).thenAnswer(new Answer<Brand>() {

            @Override
            public Brand answer(InvocationOnMock invocationOnMock) throws Throwable {
                Brand brandEntity = invocationOnMock.getArgument(0);
                brandEntity.setId(1);
                return brandEntity;
            }
        });
        // then:
        Brand brandReturn = brandService.save(brand);
        assertEquals("Apple", brandReturn.getName());
        assertEquals(1, brandReturn.getId());
    }
}
