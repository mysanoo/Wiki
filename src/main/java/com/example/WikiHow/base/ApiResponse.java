package com.example.WikiHow.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder

public class ApiResponse {

    private boolean success = true;
    private Object data;
    private String message;
}
