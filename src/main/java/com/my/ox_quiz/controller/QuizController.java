package com.my.ox_quiz.controller;

import com.my.ox_quiz.dto.MemberDto;
import com.my.ox_quiz.entity.Member;
import com.my.ox_quiz.entity.Quiz;
import com.my.ox_quiz.service.MemberService;
import com.my.ox_quiz.service.QuizService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final MemberService memberService;

    @GetMapping("/play")
    public String play(Model model) {
        Quiz quiz = quizService.getRandomQuiz();
        if (quiz == null) {
            model.addAttribute("message", "등록된 문제가 없습니다.");
            return "play";
        }
        model.addAttribute("quiz", quiz);
        return "play";
    }

    @PostMapping("/check")
    public String checkAnswer(@RequestParam Long quizId, @RequestParam Boolean answer, HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        boolean isCorrect = quizService.checkAnswer(quizId, answer);

        memberService.updateScore(loginMember.getNo(), isCorrect);

        session.setAttribute("loginMember", memberService.findById(loginMember.getNo()));

        model.addAttribute("isCorrect", isCorrect);
        return "result";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("quizList", quizService.findAllQuizzes());
        return "list";
    }

    @PostMapping("/insert")
    public String insert(Quiz quiz) {
        quizService.saveQuiz(quiz);
        return "redirect:/quiz";
    }

    @GetMapping("/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("quiz", quizService.findById(id));
        return "update";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long id, @RequestParam String content,
                         @RequestParam Boolean answer, @RequestParam String writer) {
        quizService.updateQuiz(id, content, answer, writer);
        return "redirect:/quiz";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        quizService.deleteQuiz(id);
        return "redirect:/quiz";
    }

}
