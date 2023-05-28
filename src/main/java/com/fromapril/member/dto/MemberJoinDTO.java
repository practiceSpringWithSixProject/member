package com.fromapril.member.dto;

import lombok.Getter;

@Getter
public class MemberJoinDTO extends MemberIdentifyDTO {
    private final ProfileDTO profile;

    public MemberJoinDTO(String email, String password, String nickname, String thumbnailImage, String personalStatus) {
        super(email, password);

        this.profile = new ProfileDTO(
                nickname, thumbnailImage, personalStatus
        );
    }
}
