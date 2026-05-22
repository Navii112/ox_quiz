package com.my.ox_quiz.dto;
import com.my.ox_quiz.entity.MemberStatus;
import com.my.ox_quiz.entity.RoleType;
import lombok.*;

@Getter @Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long no;
    private String id;
    private RoleType role;
    private MemberStatus status;
    private Integer answerTrue;
    private Integer answerFalse;
}