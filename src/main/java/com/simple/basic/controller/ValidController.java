package com.simple.basic.controller;

import com.simple.basic.command.QuizVO;
import com.simple.basic.command.ValidVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/valid")
public class ValidController {

    @GetMapping("/view")
    public String view(Model model) {
        model.addAttribute("vo", new ValidVO());
        return "valid/view";
    }

    @PostMapping("/actionForm")
    public String actionForm(@Valid @ModelAttribute("vo") ValidVO vo, BindingResult binding) {

        //@valid는 유효성 검사를 하겠음
        // 만약 유효성 검사에 통과하지 못하면, 통과하지 못한 멤버변수 내역이 BindingResult에 저장됩니다.
        if (binding.hasErrors()) { //내역이 있으면 true 없으면 false
            return "valid/view"; // 다시 원래 화면으로
        } else {
            return "valid/result"; // 유효성 검사 통과 페이지
        }
    }




    @GetMapping("/quiz01")
    public String quiz01(Model model){
        model.addAttribute("vo", new QuizVO());
        return "valid/quiz01";
    }



    @PostMapping("/quizForm")
    public String quizForm(@Valid @ModelAttribute("vo") QuizVO vo, BindingResult binding) {
        if (binding.hasErrors()) {
            return "valid/quiz01";
        }
        return "valid/quiz01_result";
    }
}
