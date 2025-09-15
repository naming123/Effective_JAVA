package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello") // [url]/hello로 요청
    public String hello(Model model){
        model.addAttribute("data", "hello!");// 해당 attribute의 값을 연결
        return "hello"; // resource 밑에 hello.html찾기 => templet엔진이 알아서 연결함
    }
}
