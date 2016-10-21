package com.example.Configs;

import com.example.Securities.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by kevin on 13/10/2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AppUserDetailsService appUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(appUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        auth.userDetailsService(appUserDetailsService); //password non crypté
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        http.headers().contentTypeOptions();
        http.authorizeRequests().antMatchers("/login",
                "/index",
                "/form" ,
                "/new" ,
                "/activer",
                "/forgetPassword",
                "/forget")
                .permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
                .failureHandler(authenticationFailureHandler()).defaultSuccessUrl("/index", false).permitAll().and().logout()
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/plugin/**");
    }

    @Bean
    public ExceptionMappingAuthenticationFailureHandler authenticationFailureHandler() {
        ExceptionMappingAuthenticationFailureHandler emaf = new ExceptionMappingAuthenticationFailureHandler();
        Map<String, String> exceptionMappings = new HashMap<>();
        exceptionMappings.put(LockedException.class.getCanonicalName(), "/login?error=locked");
        exceptionMappings.put(DisabledException.class.getCanonicalName(), "/login?error=disabled");
        exceptionMappings.put(BadCredentialsException.class.getCanonicalName(), "/login?error=badCredentials");
        emaf.setExceptionMappings(exceptionMappings);
        return emaf;
    }

}
