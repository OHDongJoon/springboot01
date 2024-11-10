package com.dmc.domain.category.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorySearchDto {
    private Long id;
    private Long parentId;
    private String role;
    private int depth;
}
