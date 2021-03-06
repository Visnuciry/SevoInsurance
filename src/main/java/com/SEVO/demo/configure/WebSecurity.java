package com.SEVO.demo.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.SEVO.demo.service.MyUserService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private SucessHandler sucesshandler;
	@Bean
	public UserDetailsService userDetailImplementation() {

		return new MyUserService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailImplementation()).passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests().antMatchers("/assets/**").permitAll().antMatchers("/register","/forgotPassword").permitAll()
		.antMatchers("/customer/**").hasAuthority("CUSTOMER")
		.antMatchers("/admin/**").hasAuthority("ADMIN")
		.anyRequest().authenticated().and().formLogin()
		.loginPage("/login").successHandler(sucesshandler).permitAll().and().logout()
		.deleteCookies("JSESSIONID").permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.and().rememberMe().key("uniqueAndSecret").tokenValiditySeconds(864000).and().exceptionHandling()
		.accessDeniedPage("/403");


		
	}

}
