package com.devspace.conexfy.dtos;

import java.util.Map;

import com.devspace.conexfy.enums.ConAuthTypeEnum;
import com.devspace.conexfy.enums.ConHttpMethodEnum;

public record ConConnectionResponseDTO(
        Long id,
        String name,
        String description,
        ConHttpMethodEnum method,
        ConAuthTypeEnum authType,
        Map<String, String> headersJson,
        String url,
        Map<String, String> pathVarsJson,
        Map<String, String> queryParamsJson,
        String body) {
}
