package com.fromapril.member.model.point;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class PointUse {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "point_use_id")
  private Long id;

  @Column
  private Integer point;

  @Column
  private String memo;

  @Column
  private LocalDateTime regDate;

  @Column
  @Enumerated(value = EnumType.STRING)
  private PointUseStatus status;
}
