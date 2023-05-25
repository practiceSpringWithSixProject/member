package com.fromapril.member.domain.point;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class PointSave {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "point_save_id")
  private Long id;

  private Integer point;

  private Integer balance;
  private String memo;
  private LocalDateTime regDate;
  private LocalDateTime expDate;

  @Column
  @Enumerated(value=EnumType.STRING)
  private PointSaveStaus status;

}
