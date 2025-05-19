package edu.lab.security.auth;

/*
  @author   kosta
  @project   security
  @class  AuthenticationResponse
  @version  1.0.0 
  @since 19.05.2025 - 22:57
*/

import lombok.*;

@Builder
@Getter
@Setter
public class AuthenticationResponse {
    private String token;
}
