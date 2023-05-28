package com.fromapril.member.service;

import com.fromapril.member.dto.MemberIdentifyDTO;
import com.fromapril.member.dto.MemberJoinDTO;
import com.fromapril.member.domain.member.Profile;
import com.fromapril.member.repository.MemberRepository;
import com.fromapril.member.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fromapril.member.domain.member.Member;

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
        Profile profile = Profile.createProfile(memberJoinDTO.getProfile());
        member.setProfile(profile);
        validateIsUniqueNickname(profile);

        memberRepository.save(member);
        profileRepository.save(profile);

        return member.getId();
    }

    @Transactional
    public Long update(MemberJoinDTO memberJoinDTO) {

        Member member = memberRepository
                .findByEmail(memberJoinDTO.getEmail()).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        validateIsTheMember(memberJoinDTO, member);

        Profile profile = member.getProfile();
        profile.update(profile, memberJoinDTO.getProfile());
        member.setProfile(profile);

        validateIsUniqueNickname(profile);

        memberRepository.save(member);

        return member.getId();
    }

    @Transactional
    public void leave(MemberIdentifyDTO memberIdentifyDto) {
        Member member = memberRepository.findByEmail(memberIdentifyDto.getEmail()).orElseThrow();

        validateIsTheMember(memberIdentifyDto, member);

        if(member.isLeaved()) {
            throw new IllegalArgumentException("이미 떠난자임");
        }

        member.setLeaved(true);
        memberRepository.save(member);
    }

    public Member mine(MemberIdentifyDTO memberIdentifyDto) {
        Member member = memberRepository.findByEmail(memberIdentifyDto.getEmail()).orElseThrow();

        validateIsTheMember(memberIdentifyDto, member);

        return member;
    }

    private void validateIsUniqueNickname(Profile profile) {
        Optional<Profile> foundProfile = profileRepository.findByNickname(profile.getNickname());

        if (foundProfile.isEmpty()) {
            return;
        }

        if (Objects.equals(profile.getId(), foundProfile.get().getId())) {
            return;
        }

        throw new IllegalArgumentException("닉네임 중복");
    }

    private static void validateIsTheMember(MemberIdentifyDTO memberIdentifyDto, Member member) {
        if(!member.isEquals(memberIdentifyDto)) {
            throw new IllegalArgumentException("주인 아님");
        }
    }
}
