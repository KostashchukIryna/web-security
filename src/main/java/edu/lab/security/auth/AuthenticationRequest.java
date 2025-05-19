package edu.lab.security.auth;

/*
  @author   kosta
  @project   security
  @class  AuthenticationRequest
  @version  1.0.0 
  @since 19.05.2025 - 22:57
*/
import lombok.Data;
import lombok.NonNull;
@Data
public class AuthenticationRequest {
    private String email;


    private String password;;
}
