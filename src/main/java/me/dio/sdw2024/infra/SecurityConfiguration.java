package me.dio.sdw2024.infra;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SecurityConfiguration {

		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
				return httpSecurity.csrf(csrf -> csrf.disable())
						.sessionManagement(
								management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
						.authorizeHttpRequests(authorize -> {
								 authorize.anyRequest().permitAll();
						}).build();
				// TODO: Adicionar filtro se ficar necessário

		}

}
