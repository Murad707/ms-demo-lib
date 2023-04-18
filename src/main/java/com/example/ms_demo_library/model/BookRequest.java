package com.example.ms_demo_library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    private String title;
    private String publisher;
    private Integer count;
    private Double rating;
}
