package com.costin.disertatie.eventdrivenmicroservices.apigateway.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static com.costin.disertatie.eventdrivenmicroservices.apigateway.security.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final Logger log = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
        } catch (TokenExpiredException e) {
            log.debug("Token expired: {}", e.getMessage());
            res.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        }
    }
    

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
                    .verify(token.replace(TOKEN_PREFIX, ""));

            String user = decodedJWT.getSubject();
            if (user != null) {
                String authStr = decodedJWT.getClaim(AUTHORITIES).asString();
                log.debug("getAuthentication - user = {},  authStr = {}", user, authStr);
/*
                List<SimpleGrantedAuthority> authorities = StringUtils.isNotBlank(authStr) ? Arrays
                        .stream(authStr.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                        : new ArrayList<>();
*/

                return new UsernamePasswordAuthenticationToken(user, null);
            }
        }
        return null;
    }
}