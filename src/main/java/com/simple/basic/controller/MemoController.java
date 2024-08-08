package com.simple.basic.controller;

import com.simple.basic.command.MemoVO;
import com.simple.basic.memo.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/memo")
public class MemoController {

    @Autowired
    @Qualifier("memoService")
    MemoService memoService;

    @GetMapping("/memoWrite")
    public String memoWrite(Model model) {
        model.addAttribute("memo", new MemoVO()); // 빈 VO 객체를 추가하여 폼과 바인딩
        return "memo/memoWrite";
    }

    @PostMapping("/memoForm")
    public String memoForm(@Valid @ModelAttribute("memo") MemoVO memo, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            return "memo/memoWrite";
        }
        memoService.memoWrite(memo);
        return "redirect:/memo/memoList"; // 리다이렉트하여 리스트 페이지로 이동
    }

    @GetMapping("/memoList")
    public String memoList(Model model) {
        // memoService에서 리스트 데이터를 가져와서 모델에 추가
        model.addAttribute("memoList", memoService.memoList());
        return "memo/memoList";
    }


    @PostMapping("/delete/{mno}")
    public String delete(@PathVariable("mno") Long mno) {
        memoService.memoDelete(mno);
        return "redirect:/memo/memoList";
    }


}
