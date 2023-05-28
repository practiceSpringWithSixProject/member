package com.fromapril.member.service;

import com.fromapril.member.domain.feed.Feed;
import com.fromapril.member.domain.member.Member;
import com.fromapril.member.repository.FeedRepository;
import com.fromapril.member.repository.MemberBlockContentRepository;
import com.fromapril.member.repository.MemberRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest(
        properties = {
                "schedules.cron.post.expire=* * * * * *"
        })
class PostSchedulerBatchTest {

    @Autowired FeedRepository feedRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired PostService postService;
    @Test
    public void 피드_만료_시_삭제처리() throws InterruptedException {

        Member member = memberRepository.save(Member.createMember("1", "2"));
        Long feedId = postService.write(member.getId(), "3");
        Feed feed = feedRepository.findById(feedId).orElseThrow();


        Thread.sleep(5000); // custom poll interval
        Feed foundFeed = feedRepository.findAll().get(0);
        System.out.println(foundFeed.getCreatedAt());
        System.out.println(LocalDateTime.now());

        assertTrue(foundFeed.getIsDeleted());
    }
}