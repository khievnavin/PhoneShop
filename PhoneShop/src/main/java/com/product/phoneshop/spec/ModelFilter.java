package com.product.phoneshop.spec;

import lombok.Data;

@Data
public class ModelFilter {
    private Long modelId;
    private String modelName;
    private String brandName;
    private Long brandId;
}
