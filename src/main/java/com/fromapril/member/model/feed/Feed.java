package com.fromapril.member.model.feed;

import com.fromapril.member.model.member.Member;
import com.fromapril.member.model.timeStamp.Timestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
}
