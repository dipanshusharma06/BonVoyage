package com.gl.app.userservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ApiNotFoundException extends RuntimeException{
    private String ResourceName;
    private String SubResourceName;
    private String SubResourceField;
    private String SubResourceFieldValue;

    public ApiNotFoundException(String resourceName, String subResourceName, String subResourceField, String subResourceFieldValue) {
        super(String.format("%s does not contain %s with %s : %s",resourceName,subResourceName,subResourceField,subResourceFieldValue));
        ResourceName = resourceName;
        SubResourceName = subResourceName;
        SubResourceField = subResourceField;
        SubResourceFieldValue = subResourceFieldValue;
    }
}
