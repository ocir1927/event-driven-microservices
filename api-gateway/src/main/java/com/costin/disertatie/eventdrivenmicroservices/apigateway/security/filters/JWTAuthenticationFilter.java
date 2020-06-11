package com.costin.disertatie.eventdrivenmicroservices.apigateway.security.filters;

import com.auth0.jwt.JWT;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.dto.UserLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.costin.disertatie.eventdrivenmicroservices.apigateway.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private final Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
		try {
			UserLogin creds = new ObjectMapper().readValue(req.getInputStream(), UserLogin.class);
			
			log.debug("attemptAuthentication --> user = {}", creds);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getUsername(),
							creds.getPassword(),
							new ArrayList<>())
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

//        String authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
        
//        log.debug("attemptAuthentication --> authorities = {}", authorities);

		String token = JWT.create()
				.withSubject(((User) auth.getPrincipal()).getUsername())
//				.withClaim(AUTHORITIES, authorities)
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.sign(HMAC512(SECRET.getBytes()));
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
		res.addHeader("Access-Control-Expose-Headers", HEADER_STRING);
		
		
	}

}
