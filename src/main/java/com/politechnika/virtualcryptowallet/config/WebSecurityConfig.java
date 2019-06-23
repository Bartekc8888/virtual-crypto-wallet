package com.politechnika.virtualcryptowallet.config;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

import com.politechnika.virtualcryptowallet.handler.CustomAccessDeniedHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated().and()
            .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/processLogin")
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/login?error").permitAll()
            .and()
                .logout()
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/login?logout").permitAll()
            .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler());

        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("SELECT username, password, enabled from AUTH_USERS where username = ?")
            .authoritiesByUsernameQuery("SELECT username, role from USER_ROLES where username = ?");
    }

    @Bean
    public PasswordEncoder encoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt", new BCryptPasswordEncoder(11));

        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
}