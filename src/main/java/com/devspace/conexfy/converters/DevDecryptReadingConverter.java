package com.devspace.conexfy.converters;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import com.devspace.conexfy.models.DevEncryptedString;

@ReadingConverter
public class DevDecryptReadingConverter implements Converter<DevEncryptedString, String> {
    private final StringEncryptor encryptor;

    public DevDecryptReadingConverter(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Override
    public String convert(DevEncryptedString s) {
        return encryptor.decrypt(s.getValue());
    }
}
