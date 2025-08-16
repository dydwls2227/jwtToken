package com.in28minutes.jwttokenstudy.config.jwt;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	private final Key key;

	public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey); // Base64 디코딩
		this.key = Keys.hmacShaKeyFor(keyBytes); // 디코딩된 값을 SHA alg로 key 생성
	}
}
