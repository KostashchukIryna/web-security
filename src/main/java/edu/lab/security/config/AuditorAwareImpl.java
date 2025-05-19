package edu.lab.security.config;

import org.springframework.data.domain.AuditorAware;
import java.util.Optional;
/*
  @author   kosta
  @project   security
  @class  AuditorAwareImpl
  @version  1.0.0 
  @since 19.04.2025 - 23.36
*/
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(System.getProperty("user.name"));
    }
}
