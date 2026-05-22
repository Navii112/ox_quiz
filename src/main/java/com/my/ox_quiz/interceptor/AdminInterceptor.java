package com.my.ox_quiz.interceptor;

import com.my.ox_quiz.entity.Member;
import com.my.ox_quiz.entity.RoleType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request
            , HttpServletResponse response
            , Object handler) throws Exception {
        Member loginMember = (Member) request.getSession()
                .getAttribute("loginMember");
        if (loginMember != null && loginMember.getRole() != RoleType.ADMIN) {
            response.sendRedirect("/"); // 권한 없음 처리
            return false;
        }
        return true;
    }
}
