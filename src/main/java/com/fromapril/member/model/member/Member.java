package com.fromapril.member.model.member;

import com.fromapril.member.dto.MemberIdentifyDto;
import com.fromapril.member.model.feed.Feed;
import jakarta.persistence.*;
import lombok.*;
import com.fromapril.member.model.timeStamp.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Data
@Entity
public class Member extends Timestamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="member_id")
  private Long id;

  @Column(unique = true)
  private String email;

  @Column
  private String password;

  @Column
  private boolean isLeaved;

  @OneToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="profile_id")
  private Profile profile;

  @OneToMany(mappedBy = "member")
  private List<Feed> feedList = new ArrayList<>();

  //==연관 관계 편의 메소드==//
  public void setProfile(Profile profile) {
    this.profile = profile;
    profile.setMember(this);
  }

  //==생성 메소드==//
  public static Member createMember(String email, String password) {
    Member member = new Member();
    member.setEmail(email);
    member.setPassword(password);
    member.setLeaved(false);

    return member;
  }

  //==비지니스 로직==//
  public boolean isEquals(MemberIdentifyDto memberIdentifyDto) {
    return Objects.equals(this.email, memberIdentifyDto.getEmail()) && Objects.equals(this.password, memberIdentifyDto.getPassword());
  }
}
