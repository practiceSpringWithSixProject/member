package com.fromapril.member.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ProfileDTO {
    private final String nickname;
    private final String thumbnailImage;
    private final String personalStatus;
}
