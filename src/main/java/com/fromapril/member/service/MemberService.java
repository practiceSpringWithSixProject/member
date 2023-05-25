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

        if(!Objects.equals(member.getPassword(), memberIdentifyDto.getPassword())) {
            throw new IllegalArgumentException("주인만 지울 수 있음");
        }

        if(member.isLeaved()) {
            throw new IllegalArgumentException("이미 떠난자임");
        }

        member.setLeaved(true);
        memberRepository.save(member);

        return member.getId();
    }
}
