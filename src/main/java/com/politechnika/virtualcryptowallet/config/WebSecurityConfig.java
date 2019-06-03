package com.politechnika.virtualcryptowallet.config;

import javax.sql.DataSource;

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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private SecurityUserDetailsService userDetailsService;
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/user/register").permitAll()
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
        PasswordEncoder encoder = encoder();

        auth.jdbcAuthentication().dataSource(dataSource)
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