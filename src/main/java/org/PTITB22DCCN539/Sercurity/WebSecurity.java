package org.PTITB22DCCN539.Sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurity {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtFilterBeforeAuthorization jwtFilterBeforeAuthorization;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider Provider = new DaoAuthenticationProvider();
        Provider.setUserDetailsService(userDetailsService);
        Provider.setPasswordEncoder(passwordEncoder);
        return Provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilterBeforeAuthorization, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> {
                    request
                            .requestMatchers(HttpMethod.POST, "/api/users/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/users/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/todolist/**").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/todolist/**").permitAll()
                            .anyRequest().authenticated();
                });
        httpSecurity.cors(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
}
