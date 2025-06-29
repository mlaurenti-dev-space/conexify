package com.devspace.conexfy.converters

import com.devspace.conexfy.models.DevEncryptedString
import org.jasypt.encryption.StringEncryptor
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.lang.NonNull

@ReadingConverter
class DevDecryptReadingConverter(private val encryptor: StringEncryptor) : Converter<String, DevEncryptedString> {
    override fun convert(@NonNull s: String): DevEncryptedString {
        return DevEncryptedString(encryptor.decrypt(s))
    }
}
