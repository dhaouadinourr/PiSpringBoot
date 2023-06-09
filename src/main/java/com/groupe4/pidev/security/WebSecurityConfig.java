package com.groupe4.pidev.security;

import com.groupe4.pidev.security.jwt.AuthEntryPointJwt;
import com.groupe4.pidev.security.jwt.AuthTokenFilter;
import com.groupe4.pidev.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig   {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;


    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean


    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/users").access("hasRole('ROLE_ADMIN') and isAuthenticated()")
                .antMatchers("/**").permitAll()
                .antMatchers("/api/auth/deleteuser/{id}").access("hasRole('ROLE_ADMIN') and isAuthenticated()")
                .antMatchers("/Mymission/getAll").access("hasRole('ROLE_ADMIN') and isAuthenticated()")
                .antMatchers("/Mymission/delete/{id}").access("hasRole('ROLE_ADMIN') and isAuthenticated()")
                .antMatchers("/Mymission/add").access("hasRole('ROLE_ADMIN') and isAuthenticated()")
                .antMatchers("/Mymission/update").access("hasRole('ROLE_ADMIN') and isAuthenticated()")
                .antMatchers("/Mymission/addWC/{idM}").access("hasRole('ROLE_ADMIN') and isAuthenticated()")
                .antMatchers("/Competence/add").access("hasRole('ROLE_ADMIN') and isAuthenticated()")
                .antMatchers("/Competence/update").access("hasRole('ROLE_ADMIN') and isAuthenticated()")
                .antMatchers("/Competence/getAll").access("hasRole('ROLE_ADMIN') and isAuthenticated()")
                .antMatchers("/Competence/delete/{id}").access("hasRole('ROLE_ADMIN') and isAuthenticated()");


        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    /*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**","/resetPassword");
    }*/
}
