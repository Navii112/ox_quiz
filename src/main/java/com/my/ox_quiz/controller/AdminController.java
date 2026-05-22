package com.my.ox_quiz.controller;

import com.my.ox_quiz.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;

    @GetMapping("/members")
    public String members(Model model) {
        model.addAttribute("members", memberService.findAllMembers());
        return "member-list";
    }

    @PostMapping("/member/approve")
    public String approve(@RequestParam Long no) {
        memberService.approveMember(no);
        return "redirect:/admin/members";
    }

    @PostMapping("/member/password")
    public String updatePassword(@RequestParam Long no, @RequestParam String password) {
        memberService.updatePassword(no, password);
        return "redirect:/admin/members";
    }
}