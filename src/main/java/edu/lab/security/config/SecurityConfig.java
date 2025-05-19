package edu.lab.security.config;

import edu.lab.security.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
  @author   kosta
  @project   security
  @class  SecurityConfig
  @version  1.0.0 
  @since 08.04.2025 - 22.32
*/
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtAuthFilter;


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public static Advisor preAuthorizeMethodInterceptor() {
        return AuthorizationManagerBeforeMethodInterceptor.preAuthorize();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(req -> req
                .requestMatchers("/index.html", "/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/books/hello/stranger").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/workers").hasAnyRole("ADMIN", "SUPERADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/api/v1/workers/{id}").hasAnyRole("ADMIN", "SUPERADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/api/v1/workers").hasAnyRole("ADMIN", "SUPERADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/workers").hasAnyRole("ADMIN", "SUPERADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/workers/{id}").hasRole("SUPERADMIN")
                .requestMatchers("/api/v1/workers/hello-admin").hasRole("ADMIN")
                .requestMatchers("/api/v1/workers/hello/superadmin").hasRole("SUPERADMIN")
                .requestMatchers("/api/v1/workers/hello-user").hasRole("USER")
                .requestMatchers("/api/v1/workers/hello-unknown").permitAll()
                .requestMatchers("/api/v1/workers/view/profile").hasAnyRole("USER", "ADMIN", "SUPERADMIN")
                .requestMatchers("/api/v1/workers/view/dashboard").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/v1/workers/view/stats").hasRole("SUPERADMIN")
                .anyRequest().authenticated())
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }
}
