package com.fromapril.member.service;

import com.fromapril.member.dto.MemberIdentifyDto;
import com.fromapril.member.dto.MemberJoinDTO;
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
        String nickname = "1";
        String thumbnailUrl = "2";
        String personalStatus = "3";
        MemberJoinDTO memberJoinDTO = new MemberJoinDTO(memberEmail, memberPassword, nickname, thumbnailUrl, personalStatus);

        // when
        memberService.join(memberJoinDTO);
        Member foundMember = memberRepository.findByEmail(memberEmail).orElseThrow();

        // then
        assertEquals(memberEmail, foundMember.getEmail());
        assertEquals(memberPassword, foundMember.getPassword());
        assertEquals(nickname, foundMember.getProfile().getNickname());
        assertEquals(thumbnailUrl, foundMember.getProfile().getThumbnailImage());
        assertEquals(personalStatus, foundMember.getProfile().getPersonalStatus());
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

    @Test
    public void 회원탈퇴() {
        String memberEmail = "hello@local.com";
        String memberPassword = "hello";
        Member member = Member.createMember(memberEmail, memberPassword);
        memberRepository.save(member);

        MemberIdentifyDto memberIdentifyDto = new MemberIdentifyDto(memberEmail, memberPassword);
        memberService.leave(memberIdentifyDto);

        Member foundMember = memberRepository.findById(member.getId()).orElseThrow();
        assertTrue(foundMember.isLeaved());
    }

    @Test
    public void 비밀번호틀리면_탈퇴_못함() {
        String memberEmail = "hello@local.com";
        String memberPassword = "hello";
        Member member = Member.createMember(memberEmail, memberPassword);
        memberRepository.save(member);

        MemberIdentifyDto memberIdentifyDto = new MemberIdentifyDto(memberEmail, memberPassword + "hhhh");
        assertThrows(IllegalArgumentException.class, () -> memberService.leave(memberIdentifyDto));
    }

    @Test
    public void 이미_탈퇴하면_탈퇴_못함() {
        String memberEmail = "hello@local.com";
        String memberPassword = "hello";
        Member member = Member.createMember(memberEmail, memberPassword);
        member.setLeaved(true);
        memberRepository.save(member);

        MemberIdentifyDto memberIdentifyDto = new MemberIdentifyDto(memberEmail, memberPassword + "hhhh");
        assertThrows(IllegalArgumentException.class, () -> memberService.leave(memberIdentifyDto));
    }

}