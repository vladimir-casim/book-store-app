package com.library.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

public class ImageFileUpdatedConverter implements Converter<MultipartFile, String> {
    @Override
    public String convert(MultipartFile imageFileForUpdating) {
        if (!imageFileForUpdating.isEmpty()) {
            try {
                byte[] bytes = imageFileForUpdating.getBytes();
                return Base64.getEncoder().encodeToString(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
