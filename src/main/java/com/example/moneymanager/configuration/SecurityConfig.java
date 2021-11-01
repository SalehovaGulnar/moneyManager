package com.example.moneymanager.configuration;

import com.example.moneymanager.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers( "/forgotpassword", "/register").permitAll()
                                        .antMatchers("/categories/**","/category/**").hasRole("ADMIN")
                                        .antMatchers("/types/**", "/type/**", "/").hasAnyRole("ADMIN", "USER")
                                        .antMatchers("/transaction/**").hasRole("USER")
                                        .anyRequest().authenticated()
                    .and().formLogin().loginPage("/login").permitAll()
                          .defaultSuccessUrl("/")
                          .usernameParameter("email")
                          .passwordParameter("password")
                    .and().oauth2Login().loginPage("/login").successHandler(googleOAuth2SuccessHandler)
                    .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                          .logoutSuccessUrl("/login")
                          .invalidateHttpSession(true)
                          .deleteCookies("JSESSIONID")
                    .and().exceptionHandling()
                    .and().csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customUserDetailsService);
    }

}