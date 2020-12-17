package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()

                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/sport/**").hasAnyRole("SPORT","ADMIN")
                .antMatchers("/electricity/**").hasAnyRole("ELECTRICITY","ADMIN")
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}1234")
                //    .password("{bcrypt}$2a$10$/27X4ofQBKx60PZHNmzxXOoWFKwm3ubrJjbO.NBDiHm/7X5TmpWOS")
                .roles("ADMIN")
                .and()
                .withUser("sport")
                .password("{noop}1234")

//                .password("{bcrypt}$2a$10$/27X4ofQBKx60PZHNmzxXOoWFKwm3ubrJjbO.NBDiHm/7X5TmpWOS")
                .roles("SPORT")
                .and()
                .withUser("electricity")
                .password("{noop}1234")
//                .password("{bcrypt}$2a$10$/27X4ofQBKx60PZHNmzxXOoWFKwm3ubrJjbO.NBDiHm/7X5TmpWOS")
                .roles("ELECTRICITY");
    }

}
