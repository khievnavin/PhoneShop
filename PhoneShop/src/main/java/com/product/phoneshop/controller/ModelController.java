package com.product.phoneshop.controller;


import com.product.phoneshop.exception.ServiceException;
import com.product.phoneshop.mapper.ModelMapper;
import com.product.phoneshop.model.Brand;
import com.product.phoneshop.model.Model;
import com.product.phoneshop.service.ModelService;
import com.product.phoneshop.service.dto.ModelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;
    private final ModelMapper modelMapper;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ModelDTO dto) throws SecurityException {
        Model model = modelMapper.toModel(dto);
        ModelDTO modelDTO = modelMapper.toDTO(modelService.save(model));
        return ResponseEntity.ok(modelDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) throws ServiceException {
        return ResponseEntity.ok(modelService.getById(id));
    }
}