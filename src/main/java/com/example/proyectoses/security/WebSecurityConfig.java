package com.example.proyectoses.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;





@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	private AuthenticationManager authenticationManager;
	

	private SecurityContextRepository securityContextRepository;
	
	@Bean
	public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
		return http
				.exceptionHandling()
				.authenticationEntryPoint((swe, e) -> {					
					return Mono.fromRunnable(() -> {
						swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);						
					});
				}).accessDeniedHandler((swe, e) -> {					
					return Mono.fromRunnable(() -> {						
						swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
					});
				})
				.and()
				.csrf().disable()				
				.formLogin().disable()
				.httpBasic().disable()
				.authenticationManager(authenticationManager)
				.securityContextRepository(securityContextRepository)
				.authorizeExchange()
				.pathMatchers(HttpMethod.OPTIONS).permitAll()

				//permisos de spring security
				.pathMatchers("/swagger-resources/**").permitAll()
				.pathMatchers("/swagger-ui.html").permitAll()
				.pathMatchers("/swagger-ui").permitAll()
				.pathMatchers("/v2/api-docs").permitAll()
				.pathMatchers("/webjars/**").permitAll()

				.pathMatchers("/login").permitAll()

				.pathMatchers("/cliente/**").permitAll()
				.pathMatchers("/transacciones/**").permitAll()

				.anyExchange().authenticated()
				.and().build();
	}
}
