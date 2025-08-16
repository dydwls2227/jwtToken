package com.in28minutes.jwttokenstudy.domain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.jwttokenstudy.domain.service.MemberService;
import com.in28minutes.jwttokenstudy.dto.TokenInfo;
import com.in28minutes.jwttokenstudy.dto.request.LoginDto;
import com.in28minutes.jwttokenstudy.dto.request.MemberDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/login")
	public TokenInfo login(@RequestBody LoginDto loginDto) {
		String loginId = loginDto.getLoginId();
		String password = loginDto.getPassword();
		return memberService.login(loginId, password);
	}

	@PostMapping("/join")
	public ResponseEntity<String> signUp(@RequestBody MemberDto memberDto) {
		memberService.signUp(memberDto.getLoginId(), memberDto.getPassword());
		return ResponseEntity.ok("Sign-up successful");
	}
}
