package com.in28minutes.jwttokenstudy.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.in28minutes.jwttokenstudy.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByLoginId(String loginId);
}
