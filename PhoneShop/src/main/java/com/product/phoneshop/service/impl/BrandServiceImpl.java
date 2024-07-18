package com.product.phoneshop.service.impl;

import com.product.phoneshop.mapper.BrandMapper;
import com.product.phoneshop.model.Brand;
import com.product.phoneshop.repository.BrandRepository;
import com.product.phoneshop.service.BrandService;
import com.product.phoneshop.service.dto.BrandDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

//business logic
@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    private final BrandMapper brandMapper;

    @Override
    public Brand save(BrandDTO brandDTO) {

        Brand brand = brandMapper.toEntity(brandDTO);

        return brandRepository.save(brand);
    }

    @Override
    public Brand getById(Integer id) {
       Optional<Brand> brandOptional = brandRepository.findById(id);

       if (brandOptional.isPresent()) {
           return brandOptional.get();
       }else {
           throw new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Brand not found with id: %d", id));
       }
    }

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public Brand update(Integer id, BrandDTO dto) {
        Brand brand = getById(id);
        brand.setName(dto.getName());
        brandRepository.save(brand);
        return brand;
    }

    @Override
    public void delete(Integer id) {
        Brand brand = getById(id);
        brandRepository.delete(brand);
        log.info("Brand with id: %d deleted".formatted(id));
    }
}
