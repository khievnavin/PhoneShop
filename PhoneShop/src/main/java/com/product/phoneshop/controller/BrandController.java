package com.product.phoneshop.controller;

import com.product.phoneshop.exception.ServiceException;
import com.product.phoneshop.mapper.BrandMapper;
import com.product.phoneshop.model.Brand;
import com.product.phoneshop.service.BrandService;
import com.product.phoneshop.service.dto.BrandDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    private final BrandMapper brandMapper;

    @PostMapping
    public ResponseEntity<Brand> create(@RequestBody BrandDTO brandDTO) throws ServiceException {
       // Brand brand = BrandMapper.INSTANCE.toEntity(brandDTO);
        var  brand = brandService.save(brandDTO);
        return ResponseEntity.ok(brand);
    }

    @GetMapping("{id}")
    public ResponseEntity<Brand> getById(@PathVariable("id") int id) throws ServiceException {
        return ResponseEntity.ok(brandService.getById(id));
    }

    //GetAll
    @GetMapping
    public ResponseEntity<List<BrandDTO>> getAll() {

        List<BrandDTO> listBrand = brandService.getAllBrand()
                .stream()
                .map(brandMapper::toDTO)
                .toList(); //from java 16 //collect only list
        //   .collect(Collectors.toList()); //collect for all
        return ResponseEntity.ok(listBrand);
    }

    @PutMapping("{id}")
    public ResponseEntity<Brand> update(@PathVariable("id") int id, @RequestBody BrandDTO brandDTO) throws ServiceException {
        return ResponseEntity.ok(brandService.update(id, brandDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Brand> delete(@PathVariable("id") int id) throws ServiceException {
        brandService.delete(id);
        return ResponseEntity.ok().build();
    }

}
