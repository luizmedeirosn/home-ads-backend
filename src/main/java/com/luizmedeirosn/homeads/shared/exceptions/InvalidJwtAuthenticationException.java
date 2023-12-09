package com.luizmedeirosn.homeads.shared.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException{
    
    private static final long serialVersionUID = 1L;

    public InvalidJwtAuthenticationException(String msg) {
        super(msg);
    }

}
