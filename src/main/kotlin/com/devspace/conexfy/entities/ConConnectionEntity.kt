package com.devspace.conexfy.entities

import com.devspace.conexfy.enums.ConAuthTypeEnum
import com.devspace.conexfy.enums.ConHttpMethodEnum
import com.devspace.conexfy.models.DevEncryptedString
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Sequence
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("con_connections")
class ConConnectionEntity(
    @Id
    @Sequence(sequence = "con_connections_seq", schema = "public")
    var id: Long? = null,

    @Column("name")
    var name: String,

    @Column("description")
    var description: String? = null,

    @Column("method")
    var method: ConHttpMethodEnum,

    @Column("auth_type")
    var authType: ConAuthTypeEnum,

    @Column("headers")
    var headersJson: MutableMap<String, String> = mutableMapOf(),

    @Column("url")
    var url: String,

    @Column("path_variables")
    var pathVarsJson: MutableMap<String, String> = mutableMapOf(),

    @Column("query_params")
    var queryParamsJson: MutableMap<String, String> = mutableMapOf(),

    @Column("body")
    var body: String? = null,

    // @Column("auth_username")
    // private DevEncryptedString authUsername;
    // @Column("auth_password")
    // private DevEncryptedString authPassword;
    @Column("auth_token")
    var authToken: DevEncryptedString? = null,

    // @Column("api_key")
    // private DevEncryptedString apiKey;
    @Column("read_timeout_ms")
    var readTimeoutMs: Int = 10000, // Default 10 seconds

    @Column("connect_timeout_ms")
    var connectTimeoutMs: Int = 2000, // Default 2 seconds

    @Column("depends_on")
    var dependsOn: Long? = null,

    @CreatedDate
    @Column("created_at")
    var createdAt: LocalDateTime,

    @LastModifiedDate
    @Column("updated_at")
    var updatedAt: LocalDateTime

    // @CreatedBy
    // @Column("created_by")
    // private String createdBy;
    // @LastModifiedBy
    // @Column("updated_by")
    // private String updatedBy;

)
