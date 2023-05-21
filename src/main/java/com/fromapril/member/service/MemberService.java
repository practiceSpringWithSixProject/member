package com.fromapril.member.service;

import com.fromapril.member.dtos.MemberDTO;
import com.fromapril.member.dtos.request.GetMemberDTO;
import com.fromapril.member.repository.MemberRepository;
import com.fromapril.member.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Member;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    final private MemberRepository memberRepository;
    final private ProfileRepository profileRepository;

    public MemberService(MemberRepository memberRepository, ProfileRepository profileRepository) {
        this.memberRepository = memberRepository;
        this.profileRepository = profileRepository;
    }

    public MemberDTO get(@RequestBody final GetMemberDTO getMemberDTO) {
        Member member = (Member) this.memberRepository.findByEmailAndPassword(getMemberDTO).orElseThrow(()-> new IllegalArgumentException("뭔가 틀림"));


        return null;
    }
}
