package com.fromapril.member.controller;

import com.fromapril.member.repository.MemberRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    final private MemberRepository repository;

    public MemberController(MemberRepository repository) {
        this.repository = repository;
    }
}
