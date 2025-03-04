package com.el.common.upload.application.impl.awss3;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AmazonServiceS3Exception extends RuntimeException {
    public AmazonServiceS3Exception(String message) {
        super(message);
    }

}