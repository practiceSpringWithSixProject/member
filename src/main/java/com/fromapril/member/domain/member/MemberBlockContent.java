package com.fromapril.member.domain.member;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {
                "memberId", "blockContentType"
        }),
})
@Entity
public class MemberBlockContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="profile_id")
    private Long id;

    @Column
    private Long memberId;

    @Enumerated(value = EnumType.STRING)
    private BlockContentType blockContentType;

    @Column
    private List<Long> bannedContentIdList = new ArrayList<>();
}
