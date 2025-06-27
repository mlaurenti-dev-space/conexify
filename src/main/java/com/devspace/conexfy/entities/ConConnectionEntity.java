package com.devspace.conexfy.entities;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Sequence;
import org.springframework.data.relational.core.mapping.Table;

import com.devspace.conexfy.enums.ConAuthTypeEnum;
import com.devspace.conexfy.enums.ConHttpMethodEnum;

@Table("con_connections")
public class ConConnectionEntity {
    @Id
    @Sequence(sequence = "con_connections_seq", schema = "public")
    private Long id;
    @Column("name")
    private String name;
    @Column("description")
    private String description;
    @Column("method")
    private ConHttpMethodEnum method;
    @Column("auth_type")
    private ConAuthTypeEnum authType;
    @Column("headers")
    private Map<String, String> headersJson;
    @Column("url")
    private String url;
    @Column("path_variables")
    private Map<String, String> pathVarsJson;
    @Column("query_params")
    private Map<String, String> queryParamsJson;
    @Column("body")
    private String body;

    // @Column("auth_username")
    // private DevEncryptedString authUsername;
    // @Column("auth_password")
    // private DevEncryptedString authPassword;
    // @Column("auth_token")
    // private DevEncryptedString authToken;
    // @Column("api_key")
    // private DevEncryptedString apiKey;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;

    // @CreatedBy
    // @Column("created_by")
    // private String createdBy;

    // @LastModifiedBy
    // @Column("updated_by")
    // private String updatedBy;

    public ConConnectionEntity(){};
    
    public ConConnectionEntity(Long id, String name, String description, ConHttpMethodEnum method,
            ConAuthTypeEnum authType, Map<String, String> headersJson, String url, Map<String, String> pathVarsJson,
            Map<String, String> queryParamsJson, String body, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.method = method;
        this.authType = authType;
        this.headersJson = headersJson;
        this.url = url;
        this.pathVarsJson = pathVarsJson;
        this.queryParamsJson = queryParamsJson;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getDescription() {
         return description;
     }

     public void setDescription(String description) {
         this.description = description;
     }

     public ConHttpMethodEnum getMethod() {
         return method;
     }

     public void setMethod(ConHttpMethodEnum method) {
         this.method = method;
     }

     public ConAuthTypeEnum getAuthType() {
         return authType;
     }

     public void setAuthType(ConAuthTypeEnum authType) {
         this.authType = authType;
     }

     public Map<String, String> getHeadersJson() {
         return headersJson;
     }

     public void setHeadersJson(Map<String, String> headersJson) {
         this.headersJson = headersJson;
     }

     public String getUrl() {
         return url;
     }

     public void setUrl(String url) {
         this.url = url;
     }

     public Map<String, String> getPathVarsJson() {
         return pathVarsJson;
     }

     public void setPathVarsJson(Map<String, String> pathVarsJson) {
         this.pathVarsJson = pathVarsJson;
     }

     public Map<String, String> getQueryParamsJson() {
         return queryParamsJson;
     }

     public void setQueryParamsJson(Map<String, String> queryParamsJson) {
         this.queryParamsJson = queryParamsJson;
     }

     public String getBody() {
         return body;
     }

     public void setBody(String body) {
         this.body = body;
     }

     public LocalDateTime getCreatedAt() {
         return createdAt;
     }

     public void setCreatedAt(LocalDateTime createdAt) {
         this.createdAt = createdAt;
     }

     public LocalDateTime getUpdatedAt() {
         return updatedAt;
     }

     public void setUpdatedAt(LocalDateTime updatedAt) {
         this.updatedAt = updatedAt;
     }
}
