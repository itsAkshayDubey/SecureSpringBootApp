package com.github.itsAkshayDubey.springsecuritydemo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
public class WebSecurityConfiguration {

	/*@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		return new InMemoryUserDetailsManager(
				User.withUsername("test").password(passwordEncoder().encode("secret")).roles("USER").build(),
				User.withUsername("admin").password(passwordEncoder().encode("adminsecret")).roles("ADMIN").build()
				);
	}*/

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Autowired
	private DataSource dataSource;

	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager() {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	@Order(2)
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.authorizeRequests()
		.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
		.anyRequest().authenticated();

		httpSecurity.httpBasic();

		return httpSecurity.build();
	}

	@Bean
	@Order(1)
	SecurityFilterChain securityFilterChainForDigestAuthentication(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.antMatcher("/admin/**")
		.addFilter(digestAuthenticationFilter()).exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint()).
			and()
			.authorizeRequests().antMatchers("/admin/**")
			.hasAuthority("ROLE_ADMIN")
	;
	return httpSecurity.build();
}

	private DigestAuthenticationEntryPoint authenticationEntryPoint() {
		DigestAuthenticationEntryPoint digestAuthenticationEntryPoint = new DigestAuthenticationEntryPoint();
		digestAuthenticationEntryPoint.setRealmName("admin-digest-realm");
		digestAuthenticationEntryPoint.setKey("somekey");
		return digestAuthenticationEntryPoint;
	}

	private DigestAuthenticationFilter digestAuthenticationFilter() {
		DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
		digestAuthenticationFilter.setUserDetailsService(jdbcUserDetailsManager());
		digestAuthenticationFilter.setAuthenticationEntryPoint(authenticationEntryPoint());
		return digestAuthenticationFilter;
	}
}
