package com.fromapril.member.service;

import com.fromapril.member.model.member.Member;
import com.fromapril.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void 회원가입() {
        String memberEmail = "hello@local.com";
        String memberPassword = "hello";

        // given
        Member member = new Member();
        member.setEmail(memberEmail);
        member.setPassword(memberPassword);
        member.setLeaved(false);

        // when
        memberService.join(member);
        Member foundMember = memberRepository.findByEmail(memberEmail).orElseThrow();

        // then
        assertEquals(member.getId(), foundMember.getId());
    }

}