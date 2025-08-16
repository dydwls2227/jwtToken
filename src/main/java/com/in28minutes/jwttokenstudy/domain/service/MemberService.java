package com.in28minutes.jwttokenstudy.domain.service;

import com.in28minutes.jwttokenstudy.config.jwt.JwtTokenProvider;
import com.in28minutes.jwttokenstudy.domain.entity.Member;
import com.in28minutes.jwttokenstudy.domain.repository.MemberRepository;
import com.in28minutes.jwttokenstudy.dto.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public TokenInfo login(String loginId, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginId, password);

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		return jwtTokenProvider.generateToken(authentication);
	}

	@Transactional
	public Long signUp(String loginId, String password) {
		if (memberRepository.findByLoginId(loginId).isPresent()) {
			throw new IllegalArgumentException("해당 아이디는 이미 사용중입니다.");
		}
		String encodedPassword = passwordEncoder.encode(password);
		Member member = Member.builder()
			.loginId(loginId)
			.password(encodedPassword)
			.build();
		memberRepository.save(member);
		return member.getId();
	}
}
