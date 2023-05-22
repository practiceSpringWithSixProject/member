package com.fromapril.member.service;

import com.fromapril.member.model.member.Member;
import com.fromapril.member.model.member.Profile;
import com.fromapril.member.repository.MemberRepository;
import com.fromapril.member.repository.ProfileRepository;
import jakarta.persistence.Column;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired MemberRepository memberRepository;
    @Autowired ProfileRepository profileRepository;

    @Autowired MemberService memberService;

    @Test
    public void 회원가입() {
        // given
        String memberEmail = "hello@local.com";
        String memberPassword = "hello";
        Member member = Member.createMember(memberEmail, memberPassword);

        // when
        memberService.join(member);
        Member foundMember = memberRepository.findByEmail(memberEmail).orElseThrow();

        // then
        assertEquals(member.getId(), foundMember.getId());
    }


    @Test
    public void 프로필업데이트() {
        String memberEmail = "hello@local.com";
        String memberPassword = "hello";
        Member member = Member.createMember(memberEmail, memberPassword);
        memberRepository.save(member);

        String nickname = "hello";
        String thumbnailImage = "hello";
        String personalStatus = "whatisthisfor";

        Profile profile = new Profile();
        profile.setNickname(nickname);
        profile.setThumbnailImage(thumbnailImage);
        profile.setPersonalStatus(personalStatus);
        profileRepository.save(profile);

        memberService.update(member.getId(), profile);

        assertEquals(member.getProfile(), profile);
        assertEquals(member.getProfile().getMember(), member);
    }

    // util //

}