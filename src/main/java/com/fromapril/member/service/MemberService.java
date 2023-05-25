package com.fromapril.member.service;

import com.fromapril.member.dto.MemberIdentifyDto;
import com.fromapril.member.dto.MemberJoinDTO;
import com.fromapril.member.model.member.Profile;
import com.fromapril.member.repository.MemberRepository;
import com.fromapril.member.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fromapril.member.model.member.Member;

import java.util.Objects;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    public Long join(MemberJoinDTO memberJoinDTO) {

        Member member = Member.createMember(memberJoinDTO.getEmail(), memberJoinDTO.getPassword());
        Profile profile = Profile.createProfile(memberJoinDTO.getNickname(), memberJoinDTO.getThumbnailImage(), memberJoinDTO.getPersonalStatus());
        member.setProfile(profile);

        memberRepository.save(member);
        profileRepository.save(profile);

        return member.getId();
    }

    @Transactional
    public Long update(Long memberId, Profile profile) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        member.setProfile(profile);
        memberRepository.save(member);

        return member.getId();
    }

    @Transactional
    public Long leave(MemberIdentifyDto memberIdentifyDto) {
        Member member = memberRepository.findByEmail(memberIdentifyDto.getEmail()).orElseThrow();

        validateIsTheMember(memberIdentifyDto, member);

        if(member.isLeaved()) {
            throw new IllegalArgumentException("이미 떠난자임");
        }

        member.setLeaved(true);
        memberRepository.save(member);

        return member.getId();
    }

    public Member mine(MemberIdentifyDto memberIdentifyDto) {
        Member member = memberRepository.findByEmail(memberIdentifyDto.getEmail()).orElseThrow();

        validateIsTheMember(memberIdentifyDto, member);

        return member;
    }

    private static void validateIsTheMember(MemberIdentifyDto memberIdentifyDto, Member member) {
        if(!member.isEquals(memberIdentifyDto)) {
            throw new IllegalArgumentException("주인 아님");
        }
    }
}
