package com.politechnika.virtualcryptowallet.config;

import com.politechnika.virtualcryptowallet.handler.CustomAccessDeniedHandler;
import com.politechnika.virtualcryptowallet.model.UserRole;
import com.politechnika.virtualcryptowallet.security.SecurityUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private SecurityUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/register").permitAll()
            .antMatchers("/user/register").permitAll()
            .antMatchers("/international").permitAll()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated().and()
            .formLogin().loginPage("/login").permitAll()
                        .loginProcessingUrl("/processLogin").permitAll()
                        .successForwardUrl("/dashboard")
                        .failureForwardUrl("/login").and()
            .logout().logoutSuccessUrl("/login").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = encoder();

        auth.inMemoryAuthentication()
            .withUser("user").password(encoder.encode("password")).roles(UserRole.USER.toString())
            .and()
            .withUser("admin").password(encoder.encode("admin")).roles(UserRole.ADMIN.toString());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
}