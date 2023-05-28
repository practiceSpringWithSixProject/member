package com.fromapril.member.service;

import com.fromapril.member.domain.feed.Feed;
import com.fromapril.member.domain.member.BlockContentType;
import com.fromapril.member.domain.member.Member;
import com.fromapril.member.domain.member.MemberBlockContent;
import com.fromapril.member.repository.FeedRepository;
import com.fromapril.member.repository.MemberBlockContentRepository;
import com.fromapril.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final FeedRepository feedRepository;
    private final MemberRepository memberRepository;
    private final MemberBlockContentRepository memberBlockContentRepository;

    @Transactional
    public Long write(Long memberId, String content) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        Feed newFeed = Feed.createFeed(content, member);

        feedRepository.save(newFeed);

        return newFeed.getId();
    }

    @Transactional
    public void update(Long memberId, Long feedId, String content) {
        Feed feedToUpdate = feedRepository.findById(feedId).orElseThrow();

        validateIsFeedOwner(memberId, feedToUpdate);

        feedToUpdate.setContent(content);
        feedRepository.save(feedToUpdate);

    }

    @Transactional
    public void delete(Long memberId, Long feedId) {
        Feed feedToDelete = feedRepository.findById(feedId).orElseThrow();

        validateIsFeedOwner(memberId, feedToDelete);

        feedToDelete.setIsDeleted(true);
    }

    @Transactional
    public void hidePost(Long memberId, Long feedId) {
        MemberBlockContent memberBlockContent = memberBlockContentRepository.findByMemberIdAndType(
                memberId,
                BlockContentType.POST
        ).orElseGet(() -> {
            MemberBlockContent mbc = new MemberBlockContent();
            mbc.setMemberId(memberId);
            mbc.setBlockContentType(BlockContentType.POST);

            return mbc;
        });

        memberBlockContent.getBannedContentIdList().add(feedId);

        memberBlockContentRepository.save(memberBlockContent);
    }


    private static void validateIsFeedOwner(Long memberId, Feed feedToUpdate) {
        if(!Objects.equals(feedToUpdate.getMember().getId(), memberId)) {
            throw new IllegalAccessError("자기 자신의 feed만 지울 수 있습니다.");
        }
    }
}
