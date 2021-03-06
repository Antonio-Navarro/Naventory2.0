package com.antoniojnavarro.naventory.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.antoniojnavarro.naventory.app.security.services.impl.UserDetailsServiceImpl;
import com.antoniojnavarro.naventory.model.entities.enums.RolEnum;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**", "/javax.faces.resource/**", "/errorPage.xhtml", "/session-expired.xhtml",
						"/403.xhtml", "/404.xhtml", "/too-many-sessions.xhtml", "/login.xhtml", "/registro.xhtml")
				.permitAll().and().authorizeRequests().antMatchers("/private/administracion/**")
				.hasAuthority(RolEnum.ROLE_ADMIN.name()).and().authorizeRequests().antMatchers("/private/empresa/**")
				.hasAuthority(RolEnum.ROLE_EMP.name()).anyRequest().authenticated().and().csrf().disable()
				.exceptionHandling().and().sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true)
				.expiredUrl("/too-many-sessions.xhtml");
		http.authorizeRequests();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return authenticationProvider;
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

}
