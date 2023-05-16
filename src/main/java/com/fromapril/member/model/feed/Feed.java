package com.fromapril.member.model.feed;

import com.fromapril.member.model.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Feed {
  //실제 entity 클래스
  //포스트 - 게시글이 구성해야하는 요소들
  // 타임라인 - 포스트들의 리스트들을 보여주는애

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String content;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;


}
