package com.fromapril.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class MemberJoinDTO extends MemberIdentifyDto {
    private final String nickname;
    private final String thumbnailImage;
    private final String personalStatus;

    public MemberJoinDTO(String email, String password, String nickname, String thumbnailImage, String personalStatus) {
        super(email, password);
        this.nickname = nickname;
        this.thumbnailImage = thumbnailImage;
        this.personalStatus = personalStatus;
    }
}
