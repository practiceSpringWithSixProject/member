package com.fromapril.member.service;

import com.fromapril.member.domain.feed.Feed;
import com.fromapril.member.domain.member.Member;
import com.fromapril.member.repository.FeedRepository;
import com.fromapril.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired FeedRepository feedRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired PostService postService;


    @Test
    public void 피드생성() {
        Member member = memberRepository.save(Member.createMember("1", "2"));

        Long feedId = postService.write(member.getId(), "3");
        Feed foundFeed = feedRepository.findById(feedId).orElseThrow();

        assertEquals(foundFeed.getMember().getId(), member.getId());
    }
    @Test
    public void 피드_업데이트() {
        Member member = memberRepository.save(Member.createMember("1", "2"));
        Long feedId = postService.write(member.getId(), "3");

        String feedContent = "4";

        postService.update(member.getId(), feedId,  feedContent);
        Feed foundFeed = feedRepository.findById(feedId).orElseThrow();

        assertEquals(foundFeed.getContent(), feedContent);
    }

    @Test
    public void 피드_업데이트는_지만_가능() {
        Member member = memberRepository.save(Member.createMember("1", "2"));
        Long feedId = postService.write(member.getId(), "3");

        String feedContent = "4";

        assertThrows(IllegalAccessError.class, () -> postService.update(member.getId() + 1, feedId,  feedContent));
    }

    @Test
    public void 피드_삭제() {
        Member member = memberRepository.save(Member.createMember("1", "2"));
        Long feedId = postService.write(member.getId(), "3");

        postService.delete(member.getId(), feedId);
        Optional<Feed> feed = feedRepository.findById(feedId);

        assertFalse(feed.isPresent());
    }
    @Test
    public void 피드_삭제는_지만_가능() {
        Member member = memberRepository.save(Member.createMember("1", "2"));
        Long feedId = postService.write(member.getId(), "3");

        assertThrows(IllegalAccessError.class, () -> postService.delete(member.getId() + 1, feedId));
    }

}