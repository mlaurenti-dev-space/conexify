package com.devspace.conexfy.converters;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.NonNull;

import com.devspace.conexfy.models.DevEncryptedString;

@ReadingConverter
public class DevDecryptReadingConverter implements Converter<String, DevEncryptedString> {
    private final StringEncryptor encryptor;

    public DevDecryptReadingConverter(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Override
    public DevEncryptedString convert(@NonNull String s) {
        return new DevEncryptedString(encryptor.decrypt(s));
    }
}
