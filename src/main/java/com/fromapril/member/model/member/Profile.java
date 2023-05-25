package com.fromapril.member.model.member;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="profile_id")
  private Long id;

  @Column
  private String nickname;

  @Column
  private String thumbnailImage;

  @Column
  private String personalStatus;

  @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
  private Member member;

  //==생성 메소드==//
  public static Profile createProfile(String nickname, String thumbnailImage, String personalStatus) {
    Profile profile = new Profile();
    profile.setNickname(nickname);
    profile.setThumbnailImage(thumbnailImage);
    profile.setPersonalStatus(personalStatus);

    return profile;
  }
}
