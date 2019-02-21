package com.hurynovich.spring_security_demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(final UserDetailsService userService, final PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/", "/main-page", "/profile-page",
                        "/sign-up-page", "/sign-in-page",
                        "/sign-up", "/sign-in", "/sign-out").permitAll().
                antMatchers("/user-page").hasAnyAuthority("USER", "ADMIN").
                antMatchers("/admin-page").hasAuthority("ADMIN").
                anyRequest().authenticated().
                and().
                formLogin().loginPage("/sign-in-page").loginProcessingUrl("/sign-in").defaultSuccessUrl("/profile-page", true).usernameParameter("email").passwordParameter("password").
                and().
                logout().logoutRequestMatcher(new AntPathRequestMatcher("/sign-out")).logoutSuccessUrl("/main-page").invalidateHttpSession(true).deleteCookies("JSESSIONID").clearAuthentication(true);
        http.authorizeRequests().antMatchers("/static/**").permitAll().anyRequest().permitAll();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
