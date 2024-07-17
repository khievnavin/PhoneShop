package com.product.phoneshop.controller;

import com.product.phoneshop.mapper.EntityMapper;
import com.product.phoneshop.model.Brand;
import com.product.phoneshop.service.BrandService;
import com.product.phoneshop.service.dto.BrandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping
    public ResponseEntity <Brand> create(@RequestBody BrandDTO brandDTO){
        Brand brand = EntityMapper.toBrand(brandDTO);
        brand = brandService.save(brand);
        return ResponseEntity.ok(brand);
    }

    @GetMapping("{id}")
    public ResponseEntity<Brand> getById(@PathVariable("id") int id){

        return ResponseEntity.ok(brandService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BrandDTO>> getAll(){

       List<BrandDTO> listBrand =brandService.getAllBrand()
                .stream()
                .map(EntityMapper::toBrandDTO)
                .toList(); //from java 16 //collect only list
             //   .collect(Collectors.toList()); //collect for all
        return ResponseEntity.ok(listBrand);
    }

    @PutMapping("{id}")
    public ResponseEntity<Brand> update(@PathVariable("id") int id,@RequestBody BrandDTO brandDTO){
        return ResponseEntity.ok(brandService.update(id, brandDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Brand> delete(@PathVariable("id") int id){
        brandService.delete(id);
        return ResponseEntity.ok().build();
    }

}
