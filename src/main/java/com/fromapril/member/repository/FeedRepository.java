package com.fromapril.member.repository;

import com.fromapril.member.domain.feed.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed,Long> {
    @Query("select f from Feed f where f.createdAt < :threshold")
    Optional<List<Feed>> findWithLocalTime(LocalDateTime threshold);
}
