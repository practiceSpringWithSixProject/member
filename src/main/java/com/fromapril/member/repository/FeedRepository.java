package com.fromapril.member.repository;

import com.fromapril.member.domain.feed.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed,Long> {

}
