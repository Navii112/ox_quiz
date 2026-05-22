package com.my.ox_quiz.config;

import com.my.ox_quiz.entity.Member;
import com.my.ox_quiz.entity.MemberStatus;
import com.my.ox_quiz.entity.RoleType;
import com.my.ox_quiz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final MemberRepository memberRepository;

    @Override
    public void run(String... args) throws Exception {
        if (!memberRepository.existsById("root")) {
            Member admin = Member.builder()
                    .id("root")
                    .password("1111")
                    .role(RoleType.ADMIN)
                    .status(MemberStatus.APPROVED)
                    .answerTrue(0)
                    .answerFalse(0)
                    .build();

            memberRepository.save(admin);
            System.out.println("====== 관리자 계정(root) 자동 생성 완료 ======");
        }
    }
}