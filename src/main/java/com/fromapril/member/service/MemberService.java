package com.fromapril.member.service;

import com.fromapril.member.model.member.Profile;
import com.fromapril.member.repository.MemberRepository;
import com.fromapril.member.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fromapril.member.model.member.Member;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    public Long join(Member member) {
        memberRepository.save(member);

        return member.getId();
    }

    @Transactional
    public Long update(Long memberId, Profile profile) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        member.setProfile(profile);
        memberRepository.save(member);

        return member.getId();
    }
}
