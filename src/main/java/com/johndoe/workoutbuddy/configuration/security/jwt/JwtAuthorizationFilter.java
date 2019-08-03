package com.johndoe.workoutbuddy.configuration.security.jwt;

import com.johndoe.workoutbuddy.configuration.security.SecurityConstants;
import io.jsonwebtoken.Jwts;
import lombok.extern.java.Log;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Log
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var authentication = getAuthentication(request);
        if(authentication == null) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        var token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if(token != null && !"".equals(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            try {
                var signingToken = SecurityConstants.JWT_SECRET.getBytes();
                var parsedToken = Jwts.parser().setSigningKey(signingToken).parseClaimsJws(token.replace("Bearer ", ""));
                var username = parsedToken.getBody().getSubject();
                var authorities = ((List<?>)parsedToken.getBody()
                        .get("rol")).stream()
                        .map(authority -> new SimpleGrantedAuthority((String)authority))
                        .collect(Collectors.toList());

                return !"".equals(username) ? new UsernamePasswordAuthenticationToken(username, null, authorities) : null;
            } catch (Exception e) {
                log.severe(e.getMessage());
            }
        }
        return null;
    }
}
