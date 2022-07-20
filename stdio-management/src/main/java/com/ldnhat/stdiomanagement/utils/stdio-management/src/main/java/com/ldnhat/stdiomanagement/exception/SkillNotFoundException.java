package com.ldnhat.stdiomanagement.exception;

public class SkillNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;

    public SkillNotFoundException(String resourceName, String fieldName) {
        super(String.format("%s not found with %s'", resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }
}
