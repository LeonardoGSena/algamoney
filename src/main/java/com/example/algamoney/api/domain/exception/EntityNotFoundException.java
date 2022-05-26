package com.example.algamoney.api.domain.exception;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
