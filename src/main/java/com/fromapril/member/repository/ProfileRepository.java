package com.fromapril.member.repository;

import com.fromapril.member.domain.member.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    @Query("select p from Profile p where p.nickname = :nickname")
    Optional<Profile> findByNickname(String nickname);
}
