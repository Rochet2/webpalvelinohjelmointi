package hellothymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloThymeleafController {

    @GetMapping("/")
    String test() {
        return "index";
    }

    @GetMapping("/video")
    String test2() {
        return "video";
    }
}
