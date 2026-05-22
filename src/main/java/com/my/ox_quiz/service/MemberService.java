package com.my.ox_quiz.service;

import com.my.ox_quiz.entity.Member;
import com.my.ox_quiz.entity.MemberStatus;
import com.my.ox_quiz.entity.RoleType;
import com.my.ox_quiz.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void join(Member member) {
        if (memberRepository.existsById(member.getId())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }

        member.setRole(RoleType.USER);
        member.setStatus(MemberStatus.PENDING);

        memberRepository.save(member);
    }

    public Member login(String id, String password) {
        return memberRepository.findById(id)
                .filter(m -> m.getPassword().equals(password)) // 비밀번호 일치 확인 (실무에서는 PasswordEncoder 사용)
                .orElse(null);
    }

    public Member findById(Long no) {
        return memberRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    @Transactional
    public void updateScore(Long no, boolean isCorrect) {
        Member member = findById(no);
        if (isCorrect) {
            member.addAnswerTrue();
        } else {
            member.addAnswerFalse();
        }
    }

    @Transactional
    public void updatePassword(Long no, String newPassword) {
        Member member = findById(no);
        member.setPassword(newPassword);
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public void approveMember(Long no) {
        Member member = findById(no);
        if (member.getRole() == RoleType.USER) {
            member.setStatus(MemberStatus.APPROVED);
        }
    }
}
