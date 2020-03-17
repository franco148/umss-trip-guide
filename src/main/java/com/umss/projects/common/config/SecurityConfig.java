package com.umss.projects.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
	
	private AuthenticationEntryPoint authEntryPoint;
	
	
	public SecurityConfig(AuthenticationEntryPoint authEntryPoint) {
		this.authEntryPoint = authEntryPoint;
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("franco").password("{noop}franco").authorities("ROLE_USER")
			.and()
			.withUser("admin").password("{noop}admin").authorities("ROLE_ADMIN", "ROLE_USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//	This configuration is going to work when we are working with Spring MVC using forms.
//		http.authorizeRequests().antMatchers("/", "/h2-console").permitAll()
//			.anyRequest().authenticated()
//			.and()
//			.formLogin()
//			.permitAll();
		
		// For RestFul based APIs we need the following configuration with Basic Authentication
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST, "/users").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.authenticationEntryPoint(authEntryPoint);
			
	}
	
}
