package com.fromapril.member.repository;

import com.fromapril.member.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m from Member m where m.email = :email")
    Optional<Member> findByEmail(String email);
}
