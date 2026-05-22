package com.my.ox_quiz.interceptor;

import com.my.ox_quiz.entity.Member;
import com.my.ox_quiz.entity.MemberStatus;
import com.my.ox_quiz.entity.RoleType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class ApprovedInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request
            , HttpServletResponse response
            , Object handler) throws Exception {
        Member loginMember = (Member) request.getSession()
                .getAttribute("loginMember");
        if (loginMember != null && loginMember.getRole() == RoleType.USER && loginMember
                .getStatus() == MemberStatus.PENDING) {
            response.sendRedirect("/member/my-page"); // 대기 상태면 마이페이지로 이동
            return false;
        }
        return true;
    }
}
