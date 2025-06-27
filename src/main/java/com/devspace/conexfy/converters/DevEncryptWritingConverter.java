package com.devspace.conexfy.converters;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.lang.NonNull;

import com.devspace.conexfy.models.DevEncryptedString;

@WritingConverter
public class DevEncryptWritingConverter implements Converter<DevEncryptedString, String> {
    private final StringEncryptor encryptor;

    public DevEncryptWritingConverter(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Override
    public String convert(@NonNull DevEncryptedString s) {
        return encryptor.encrypt(s.getValue());
    }
}
