package org.PTITB22DCCN539.Sercurity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.PTITB22DCCN539.Exception.MyException.MyException;
import org.PTITB22DCCN539.Model.Entity.UserEntity;
import org.PTITB22DCCN539.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilterBeforeAuthorization extends OncePerRequestFilter {
    @Autowired
    private JWTGenToken jwtGenToken;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        if(isTheRequestNotCheckToken(request)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(null, null);
            usernamePasswordAuthenticationToken.setAuthenticated(false);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            filterChain.doFilter(request, response);
            return;
        }
        String authorization = request.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }
        String token = authorization.substring(7);
        String email = jwtGenToken.getEmailFromToken(token);
        if(email != null || SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            if(jwtGenToken.validationToken(userDetails, token)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                                userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private Boolean isTheRequestNotCheckToken(HttpServletRequest request) {
        List<Pair<String, String>> requestNotCheck = List.of(
                Pair.of("/api/users/", "POST")
        );
        for(Pair<String, String> req : requestNotCheck) {
            if(request.getMethod().equals(req.getSecond()) &&
            request.getServletPath().contains(req.getFirst())) return true;
        }
        return false;
    }
}
