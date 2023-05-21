package com.fromapril.member.model.member;

import com.fromapril.member.model.feed.Feed;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fromapril.member.model.timeStamp.Timestamp;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member extends Timestamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="member_id")
  private Long id;

  @Column
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

}
