package com.devspace.conexfy.converters

import com.devspace.conexfy.models.DevEncryptedString
import org.jasypt.encryption.StringEncryptor
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import org.springframework.lang.NonNull

@WritingConverter
class DevEncryptWritingConverter(private val encryptor: StringEncryptor) : Converter<DevEncryptedString, String> {
    override fun convert(@NonNull s: DevEncryptedString): String {
        return encryptor.encrypt(s.value)
    }
}
