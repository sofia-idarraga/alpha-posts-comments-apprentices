package com.sofka.alphapostcomments.application.generic.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {

    private static final long serialVersionUID=2L;

    String username;
    String password;
}
