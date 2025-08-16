package com.in28minutes.jwttokenstudy.domain.service;

import com.in28minutes.jwttokenstudy.domain.entity.Member;
import com.in28minutes.jwttokenstudy.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 사용자 정보 확인
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return memberRepository.findByLoginId(username)
			.map(this::createUserDetails)
			.orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
	}

	private UserDetails createUserDetails(Member member) {
		return User.builder()
			.username(member.getUsername())
			.password(member.getPassword())
			.roles("USER")
			.build();
	}
}
