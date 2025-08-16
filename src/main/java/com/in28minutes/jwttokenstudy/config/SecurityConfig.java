package com.in28minutes.jwttokenstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.in28minutes.jwttokenstudy.config.jwt.JwtAuthenticationFilter;
import com.in28minutes.jwttokenstudy.config.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtTokenProvider jwtTokenProvider;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			// httpBasic, csrf, session 비활성화
			.httpBasic((basic) -> basic.disable())
			.csrf((csrf) -> csrf.disable())
			.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

			// 요청 경로에 대한 인가 설정
			.authorizeHttpRequests((authorize) -> authorize
				// "/members/login", "/members/join" 경로는 모든 사용자에게 허용
				.requestMatchers("/members/login", "/members/join").permitAll()
				// 그 외의 모든 경로는 인증된 사용자만 접근 가능
				.anyRequest().authenticated())

			// JWT 인증 필터를 UsernamePasswordAuthenticationFilter 앞에 추가
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
