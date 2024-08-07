package com.simple.basic.controller;

import com.simple.basic.command.ValidVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/valid")
public class ValidController {
    @GetMapping("/view")
    public String view() {

        return "valid/view";
    }

    @PostMapping("/actionForm")
    public String actionForm(@Valid ValidVO vo , BindingResult binding , Model model) {


        //@valid는 유효성 검사를 하겠음
        // 만약 유효성 검사에 통과하지 못하면 , 통과하지 못한 멤버변수 내역이 BindingResult에 저장됩니다.
        if (binding.hasErrors()) { //내역이 있으면 true 없으면 flase
            System.out.println("유효성 검사 실패");

            List<FieldError> list = binding.getFieldErrors(); // 유효성 검사에 실패한 목록
            for(FieldError err : list) {
                System.out.println(err.getField()); // 유효성 검사에 실패한 필드명
                System.out.println(err.getDefaultMessage()); // 유효성 검사에 실패한 내역의 메세지값
            }

            return "valid/errorPage"; // 유효성 검사 오류 페이지

        }

        // 유효성 검사가 성공한 경우 결과 페이지로 포워딩
        model.addAttribute("vo", vo);
        return "valid/result"; // 유효성 검사 통과 페이지
    }
}