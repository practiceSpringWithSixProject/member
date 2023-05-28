package com.fromapril.member.repository;

import com.fromapril.member.domain.member.BlockContentType;
import com.fromapril.member.domain.member.MemberBlockContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberBlockContentRepository extends JpaRepository<MemberBlockContent, Long> {

    @Query("select mbc from MemberBlockContent mbc where mbc.blockContentType = :type and mbc.memberId = :memberId")
    Optional<MemberBlockContent> findByMemberIdAndType(@Param("memberId") Long memberId, @Param("type") BlockContentType type);
}
