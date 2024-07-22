package com.product.phoneshop.service.dto;

import lombok.Data;
import java.util.List;

@Data
public class PageDTO {
    private List<?> list;
    private PaginationDTO pagination;
}
