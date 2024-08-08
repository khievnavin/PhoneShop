package com.product.phoneshop.service;

import com.product.phoneshop.model.Brand;
import com.product.phoneshop.model.Model;
import com.product.phoneshop.repository.ModelRepository;
import com.product.phoneshop.service.impl.ModelServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ModelServiceTest {

    @Mock
    private ModelRepository modelRepository;

    @Mock
    private BrandService brandService;

    private ModelService modelService;

    @BeforeEach
    public void setup(){
        modelService = new ModelServiceImpl(modelRepository,brandService);
    }

    @Test
    public void testSaveModel(){
        //define :
        // given: data we test
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Apple");
        Model model = new Model();
        model.setBrand(brand);
        model.setName("Iphone");

        // when: mock (test on service)
        Model modelSave= modelService.save(model);
        // then:
        verify(modelRepository ,  times(1)).save(model);
    }
}
