package ru.razbejkin.electronicQueue.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.razbejkin.electronicQueue.security.filter.CustomAuthenticationFilter;
import ru.razbejkin.electronicQueue.security.filter.CustomAuthorizationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login/**").permitAll();
        http.authorizeRequests().antMatchers("/api/person/registration").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/ticket/free-tickets").permitAll();
        http.authorizeRequests().antMatchers("/api/person/**").hasAnyAuthority("ROLE_PERSON");
        http.authorizeRequests().antMatchers(GET,"/api/live-queue/").hasAnyAuthority("ROLE_PERSON","ROLE_MANAGER");
        http.authorizeRequests().antMatchers(PUT,"/api/live-queue/next-person").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeRequests().antMatchers(POST,"/api/live-queue/get-to-line").hasAnyAuthority("ROLE_PERSON");
        http.authorizeRequests().antMatchers(DELETE,"/api/live-queue/get-out").hasAnyAuthority("ROLE_PERSON");
        http.authorizeRequests().antMatchers(DELETE,"/api/reception/**").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeRequests().antMatchers(DELETE,"/api/reception/end-meeting").hasAnyAuthority("ROLE_PERSON");
        http.authorizeRequests().antMatchers(GET,"/api/ticket/all-tickets").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeRequests().antMatchers(GET,"/api/ticket/near-active-ticket").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeRequests().antMatchers(DELETE,"/api/ticket").hasAnyAuthority("ROLE_PERSON","ROLE_MANAGER");
        http.authorizeRequests().antMatchers(PUT,"/api/ticket/confirm/**").hasAnyAuthority("ROLE_PERSON");
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
