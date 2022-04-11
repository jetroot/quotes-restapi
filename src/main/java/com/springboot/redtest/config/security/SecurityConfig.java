package com.springboot.redtest.config.security;

import com.springboot.redtest.config.password.PasswordConfig;
import com.springboot.redtest.custom.CustomUserDetailsServiceImpl;
import com.springboot.redtest.filter.AuthenticationFilter;
import com.springboot.redtest.filter.JwtFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordConfig passwordConfig;
    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final JwtFilter jwtFilter;

    public SecurityConfig(CustomUserDetailsServiceImpl customUserDetailsService, PasswordConfig passwordConfig, JwtFilter jwtFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordConfig = passwordConfig;
        this.jwtFilter = jwtFilter;
    }

    // For Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordConfig.bPasswordEncoder());
    }

    // Changing default login url
    protected AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl("/users/login");
        return authenticationFilter;
    }

    // For Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/createUser").permitAll()
                .antMatchers(HttpMethod.POST, "/users/login").permitAll()
//                .antMatchers(HttpMethod.POST, "/users/categories").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(getAuthenticationFilter())
                .addFilterAfter(jwtFilter, AuthenticationFilter.class);
    }

}
