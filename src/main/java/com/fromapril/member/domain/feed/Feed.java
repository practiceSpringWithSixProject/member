package com.fromapril.member.domain.feed;

import com.fromapril.member.domain.member.Member;
import com.fromapril.member.domain.timeStamp.Timestamp;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Feed extends Timestamp {
  //실제 entity 클래스
  //포스트 - 게시글이 구성해야하는 요소들
  // 타임라인 - 포스트들의 리스트들을 보여주는애

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="feed_id")
  private Long id;

  @Column
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  //==생성 편의 메소드==//
  public void setMember(Member member) {
    this.member = member;
    member.getFeedList().add(this);
  }
}
