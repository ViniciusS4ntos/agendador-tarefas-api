package com.vinicius.agendador_tarefas_api.infrastructure.exceptions;

import javax.naming.AuthenticationException;

public class UnathorizedException extends AuthenticationException {
    public UnathorizedException(String message) {
        super(message);
    }

    public UnathorizedException(String message, Throwable throwable) {
        super(message);
    }
}
