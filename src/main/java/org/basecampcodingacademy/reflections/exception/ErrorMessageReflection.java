package org.basecampcodingacademy.reflections.exception;

public class ErrorMessageReflection extends Exception {
    public Integer reflectionId;
    public ErrorMessageReflection(Integer reflectionId) {
        this.reflectionId = reflectionId;
    }
}
