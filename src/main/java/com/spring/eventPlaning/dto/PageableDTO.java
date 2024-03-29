package com.spring.eventPlaning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableDTO {
    private int page;
    private int size;
    private String sortField;
    private String sortOrder;
}