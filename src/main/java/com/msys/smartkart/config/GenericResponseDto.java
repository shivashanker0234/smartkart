package com.msys.smartkart.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GenericResponseDto<T> {
    private int statusCode;
    private T responseData;
}

