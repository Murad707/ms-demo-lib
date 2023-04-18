package com.example.ms_demo_library.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientStoreDtoRequest {
    private String name;
    private Integer amount;
    private Double rating;
}
