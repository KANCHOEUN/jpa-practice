package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) { // data를 넣어 View에 넘김
        model.addAttribute("data", "hello :)");
        return "hello"; // resources/templates 아래 있는 View 이름
    }
}
