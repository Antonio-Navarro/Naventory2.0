//package com.antoniojnavarro.naventory.app.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true, securedEnabled = true)
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService());
//		auth.authenticationProvider(authenticationProvider());
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				.antMatchers("/resources/**", "/javax.faces.resource/**", "/errorPage.xhtml", "/session-expired.xhtml",
//						"/403.xhtml", "/404.xhtml", "/too-many-sessions.xhtml", "/login.xhtml")
//				.permitAll().anyRequest().authenticated().and().csrf()
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().exceptionHandling().and()
//				.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/too-many-sessionse.xhtml");
//	}
//
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService());
//		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
//		return authenticationProvider;
//	}
//
//	@Bean
//	@Override
//	protected AuthenticationManager authenticationManager() throws Exception {
//		return super.authenticationManager();
//	}
//
////	@Bean
////	public UserDetailsService userDetailsService() {
////		return new UserDetailsServiceImpl();
////	}
//
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder(11);
//	}
//}
