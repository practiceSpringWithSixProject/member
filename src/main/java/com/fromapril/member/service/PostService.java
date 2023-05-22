package com.fromapril.member.service;

import com.fromapril.member.model.feed.Feed;
import com.fromapril.member.model.member.Member;
import com.fromapril.member.repository.FeedRepository;
import com.fromapril.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;

    public Long write(Long memberId, String content) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        Feed newFeed = new Feed();
        newFeed.setContent(content);
        newFeed.setMember(member);

        feedRepository.save(newFeed);
        return newFeed.getId();
    }

    public Long update(Long memberId, Long feedId, String content) {
        Feed feedToUpdate = feedRepository.findById(feedId).orElseThrow();

        validateIsFeedOwner(memberId, feedToUpdate);

        feedToUpdate.setContent(content);
        feedRepository.save(feedToUpdate);

        return feedToUpdate.getId();
    }

    public void delete(Long memberId, Long feedId) {
        Feed feedToDelete = feedRepository.findById(feedId).orElseThrow();

        validateIsFeedOwner(memberId, feedToDelete);

        feedRepository.delete(feedToDelete);
    }

    private static void validateIsFeedOwner(Long memberId, Feed feedToUpdate) {
        if(!Objects.equals(feedToUpdate.getMember().getId(), memberId)) {
            throw new IllegalAccessError("자기 자신의 feed만 지울 수 있습니다.");
        }
    }
}
