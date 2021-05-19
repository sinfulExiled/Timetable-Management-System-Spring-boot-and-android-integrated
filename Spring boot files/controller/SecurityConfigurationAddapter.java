package com.cb007727.EEATimeTableManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfigurationAddapter extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService);
	}

	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable();
		 
	      /*  http.authorizeRequests()
	                .antMatchers("/admin").hasRole("ADMIN")
	                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
	                .antMatchers("/").permitAll()
	              
	                .and().formLogin();*/
	    }
	
	@Bean
	public PasswordEncoder getPasswordEncoder() { return NoOpPasswordEncoder.getInstance(); }
	

}