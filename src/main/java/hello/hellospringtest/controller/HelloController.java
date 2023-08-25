package hello.hellospringtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";//viewreslover가 화면을 찾아서 처리 => 스프링 부트 템플릿 엔진은 기본 viewName 매핑 : resource/template에서 찾는다
    }
}
