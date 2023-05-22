package com.fromapril.member.service;

import com.fromapril.member.repository.MemberRepository;
import com.fromapril.member.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fromapril.member.model.member.Member;



@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    public Long join(Member member) {
        memberRepository.save(member);

        return member.getId();
    }
}
