package com.product.phoneshop.controller;


import com.product.phoneshop.exception.ServiceException;
import com.product.phoneshop.mapper.ModelEntityMapper;
import com.product.phoneshop.mapper.PageMapper;
import com.product.phoneshop.model.Model;
import com.product.phoneshop.service.ModelService;
import com.product.phoneshop.dto.ModelDTO;
import com.product.phoneshop.dto.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;
    private final ModelEntityMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ModelDTO dto) throws SecurityException {
        Model model = modelMapper.toModel(dto);
        ModelDTO modelDTO = modelMapper.toDTO(modelService.save(model));
        return ResponseEntity.ok(modelDTO);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) throws ServiceException {
        ModelDTO model = modelMapper.toDTO(modelService.getById(id));
        return ResponseEntity.ok(model);
    }

    @GetMapping
    public ResponseEntity<?> getModelList(@RequestParam Map<String, String> params){
        Page<Model> page = modelService.getModels(params);

        PageDTO dto = PageMapper.INSTANCE.toDTO(page);
        dto.setList(page.get().map(modelMapper.INSTANCE::toDTO).toList());
        return ResponseEntity.ok(dto);
    }
}