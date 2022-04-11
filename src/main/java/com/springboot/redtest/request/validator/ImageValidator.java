package com.springboot.redtest.request.validator;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageValidator implements ConstraintValidator<Image, MultipartFile> {
    // Mime type allowed in uploading images
    final String[] extensions = {"image/png", "image/jpg", "image/gif", "image/jpeg"};

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {

        if (multipartFile.getContentType() == null)
            return false;

        for ( String extension : extensions ) {
            if (extension.equals(multipartFile.getContentType()))
                return true; // extension is allowed
        }

        return false; // extension is not allowed
    }
}
