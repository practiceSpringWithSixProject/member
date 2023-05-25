package com.fromapril.member.dto;

import com.fromapril.member.domain.feed.Feed;
import com.fromapril.member.domain.member.Profile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Member;

@Getter
@RequiredArgsConstructor
public class PostDTO {
    private final String content;
    private final String nickname;
    private final String thumbnailImage;

    public static PostDTO fromEntity(Feed feed, Profile profile) {
        return new PostDTO(feed.getContent(), profile.getNickname(), profile.getThumbnailImage());
    }
}
