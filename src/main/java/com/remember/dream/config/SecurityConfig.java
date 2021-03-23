package com.remember.dream.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override // 커멘드 n으로 오버라이딩
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.mvcMatchers("/", "/save-dream", "/api/dreams/**","/h2-console/**"). //.mvcMatchers(HttpMethod.GET, "/read_dream/*").permitAll()
                .anyRequest().permitAll()
                .and()
                .csrf()
                    .ignoringAntMatchers("/h2-console/**")
                .and()
                .headers()
                    .addHeaderWriter(
                            new XFrameOptionsHeaderWriter(
                                    new WhiteListedAllowFromStrategy(Arrays.asList("localhost")) //Todo 디프레시드 된 메소드
                            )
                    ).frameOptions().sameOrigin();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}