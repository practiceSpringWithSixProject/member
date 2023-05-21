package com.fromapril.member.model.point;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class PointUseDetail {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "point_use_detail_id")
  private Long id;

  @Column
  private Integer point;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "point_save_id")
  private PointSave pointSave;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "point_use_id")
  private PointUse pointUse;
}
