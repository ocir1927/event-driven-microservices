package com.costin.disertatie.eventdrivenmicroservices.apigateway.security;

import com.costin.disertatie.eventdrivenmicroservices.apigateway.security.filters.JWTAuthenticationFilter;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.security.filters.JWTAuthorizationFilter;
import com.costin.disertatie.eventdrivenmicroservices.apigateway.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(UserService userService) {
        this.userDetailsService = userService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(authenticationManager());
//        authenticationFilter.setFilterProcessesUrl("/login");

        http.httpBasic().disable();

        http.cors().and().csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/**").permitAll()
//                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll();
//                .and()
//                .addFilter(authenticationFilter)
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                // handle an authorized attempts
//                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED));
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
