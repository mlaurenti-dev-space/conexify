package com.devspace.conexfy.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("con_connections")
public class ConConecctionEntity {
    @Id
    private Long id;

    private String name;
    private String description;
    private String url;

    @Column("method")
    private String method; // o tu enum convertido

    @Column("headers")
    private String headersJson; // o usa converter/map

    @Column("body")
    private String body;

    @Column("path_variables")
    private String pathVarsJson;

    @Column("query_params")
    private String queryParamsJson;

    @Column("auth_username")
    private String authUsername;
    @Column("auth_password")
    private String authPassword;
    @Column("auth_token")
    private String authToken;
    @Column("api_key")
    private String apiKey;

    @Column("created_at")
    private LocalDateTime createdAt;
    @Column("updated_at")
    private LocalDateTime updatedAt;
}
