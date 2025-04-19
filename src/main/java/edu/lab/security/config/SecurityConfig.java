package edu.lab.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

/*
  @author   kosta
  @project   security
  @class  SecurityConfig
  @version  1.0.0 
  @since 08.04.2025 - 22.32
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(req ->
                req.requestMatchers("/index.html").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/v1/workers").hasAnyRole("ADMIN", "SUPERADMIN", "USER")
                    .requestMatchers(HttpMethod.GET, "/api/v1/workers/{id}").hasAnyRole("ADMIN", "SUPERADMIN", "USER")
                    .requestMatchers(HttpMethod.POST, "/api/v1/workers").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/v1/workers").hasAnyRole("ADMIN", "SUPERADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/workers/{id}").hasRole("SUPERADMIN") // DELETE доступно лише SUPERADMIN
                    .requestMatchers("/api/v1/workers/hello-admin").hasRole("ADMIN")
                    .requestMatchers("/api/v1/workers/hello/superadmin").hasRole("SUPERADMIN")
                    .requestMatchers("/api/v1/workers/hello-user").hasRole("USER")
                    .requestMatchers("/api/v1/workers/hello-unknown").permitAll()

                    .requestMatchers("/api/v1/workers/view/profile").hasAnyRole("USER", "ADMIN", "SUPERADMIN")
                    .requestMatchers("/api/v1/workers/view/dashboard").hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/api/v1/workers/view/stats").hasRole("SUPERADMIN")
                    .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("ADMIN")
            .build();

        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("user"))
            .roles("USER")
            .build();

        UserDetails superadmin = User.builder()
            .username("superadmin")
            .password(passwordEncoder().encode("superadmin"))
            .roles("SUPERADMIN")
            .build();


        return new InMemoryUserDetailsManager(admin, user, superadmin);
    }
}
