package com.in28minutes.jwttokenstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenInfo {

	private String grantType; // JWT에 대한 인증 타입 (여기서는 Bearer 사용)
	private String accessToken;
	private String refreshToken;
}
