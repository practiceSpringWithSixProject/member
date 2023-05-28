package com.fromapril.member.dto;

import lombok.Getter;

public record MemberWithProfileDTO(Integer memberId, String nickname, String thumbnailImage, String privateStatus) {
}
