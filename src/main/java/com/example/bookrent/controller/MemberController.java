package com.example.bookrent.controller;

import com.example.bookrent.domain.Member;
import com.example.bookrent.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    // 의존성 주입
    final private MemberService memberService;

    @GetMapping("/logout")
    public String Logout(HttpSession session) {
        session.removeAttribute("loggedIn");
        System.out.println("로그아웃 됨");
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model m, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {

        if (memberService.authenticateUser(username, password)) {
            session.setAttribute("loggedIn", true);

            Member member = memberService.getUser(username);
            session.setAttribute("member", member);

            return "redirect:/";
        } else {
            m.addAttribute("error", true);

            return "login";
        }
    }

    @GetMapping("/signup")
    public String signup(SignUpForm signUpForm) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignUpForm signUpForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        if (!signUpForm.getPassword().equals(signUpForm.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "passwordInCorrect", "패스워드가 일치하지 않습니다.");
            return "signup";
        }
            memberService.create(signUpForm.getUsername(), signUpForm.getConfirmPassword() );

            return "redirect:/";
        }

}
