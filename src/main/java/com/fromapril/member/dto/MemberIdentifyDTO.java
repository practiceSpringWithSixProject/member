package com.fromapril.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class MemberIdentifyDTO {
    private final String email;
    private final String password;

    public Boolean isEquals(MemberIdentifyDTO memberIdentifyDto) {
        return Objects.equals(email, memberIdentifyDto.email) && Objects.equals(password, memberIdentifyDto.password);
    }
}
