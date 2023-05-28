package com.fromapril.member.service;

import com.fromapril.member.domain.feed.Feed;
import com.fromapril.member.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostSchedulerBatch {
    private final FeedRepository feedRepository;

    @Scheduled(cron = "${schedules.cron.post.expire}")
    public void markDeleteExpiredFeed() {
        LocalDateTime time = LocalDateTime.now();
//        List<Feed> feedList = feedRepository.findWithLocalTime(time).orElseThrow();
        List<Feed> feedList = feedRepository.findAll();

        System.out.println(feedList.size());
        System.out.println(time);

        if (feedList.size() < 1) {
            return;
        }

        feedRepository.saveAllAndFlush(
                feedList
                        .stream()
                        .peek((feed) -> feed.setIsDeleted(true))
                        .collect(Collectors.toList())
        );
    }
}
