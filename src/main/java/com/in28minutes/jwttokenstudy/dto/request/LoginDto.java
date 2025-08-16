package com.in28minutes.jwttokenstudy.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
	private String loginId;
	private String password;
}
