package com.devspace.conexfy.dtos

import com.devspace.conexfy.enums.ConAuthTypeEnum
import com.devspace.conexfy.enums.ConHttpMethodEnum
import com.devspace.conexfy.models.DevEncryptedString

data class ConConnectionRequestDTO(
    val name: String?,
    val description: String?,
    val method: ConHttpMethodEnum?,
    val authType: ConAuthTypeEnum?,
    val headersJson: MutableMap<String, String>?,
    val url: String?,
    val pathVarsJson: MutableMap<String, String>?,
    val queryParamsJson: MutableMap<String, String>?,
    val body: String?,
    val authToken: DevEncryptedString?,
    val dependsOn: Int?
)
