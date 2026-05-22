package com.my.ox_quiz.controller;

import com.my.ox_quiz.entity.Member;
import com.my.ox_quiz.entity.RoleType;
import com.my.ox_quiz.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm() { return "join"; }

    @PostMapping("/join")
    public String join(@ModelAttribute Member member) {
        memberService.join(member);
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String loginForm() { return "login"; }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password, HttpServletRequest request) {
        Member loginMember = memberService.login(id, password);
        if (loginMember != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", loginMember);

            if (loginMember.getRole() == RoleType.ADMIN) {
                return "redirect:/quiz";
            }
            return "redirect:/member/my-page";
        }
        return "redirect:/member/login?error";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) { session.invalidate(); }
        return "redirect:/";
    }

    @GetMapping("/my-page")
    public String myPage(HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        model.addAttribute("member", memberService.findById(loginMember.getNo()));
        return "my-page";
    }

    @PostMapping("/password")
    public String updatePassword(@RequestParam String password, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        memberService.updatePassword(loginMember.getNo(), password);
        return "redirect:/member/my-page";
    }
}