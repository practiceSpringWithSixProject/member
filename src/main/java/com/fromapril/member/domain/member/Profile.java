package com.fromapril.member.domain.member;

import com.fromapril.member.dto.ProfileDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  public static Profile createProfile(ProfileDTO profileDTO) {
    Profile profile = new Profile();
    profile.setNickname(profileDTO.getNickname());
    profile.setThumbnailImage(profileDTO.getThumbnailImage());
    profile.setPersonalStatus(profileDTO.getPersonalStatus());

    return profile;
  }

  //==비지니스 로직==//
  public Profile update(Profile profile, ProfileDTO profileDTO) {
    profile.setNickname(profileDTO.getNickname());
    profile.setThumbnailImage(profileDTO.getThumbnailImage());
    profile.setPersonalStatus(profileDTO.getPersonalStatus());

    return profile;
  }
}
