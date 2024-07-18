package com.product.phoneshop.controller;

import com.product.phoneshop.mapper.BrandMapper;
import com.product.phoneshop.mapper.ModelMapper;
import com.product.phoneshop.model.Model;
import com.product.phoneshop.service.ModelService;
import com.product.phoneshop.service.dto.ModelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;
    private final ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ModelDTO dto) {
        Model model = modelMapper.toModel(dto);

        ModelDTO modelDTO = modelMapper.toDTO(modelService.save(model));
        return ResponseEntity.ok(modelDTO);
    }
}
