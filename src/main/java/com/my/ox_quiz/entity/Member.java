package com.my.ox_quiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Column(columnDefinition = "integer default 0")
    private Integer answerTrue = 0;

    @Column(columnDefinition = "integer default 0")
    private Integer answerFalse = 0;

    public void addAnswerTrue() { this.answerTrue++; }
    public void addAnswerFalse() { this.answerFalse++; }
}

