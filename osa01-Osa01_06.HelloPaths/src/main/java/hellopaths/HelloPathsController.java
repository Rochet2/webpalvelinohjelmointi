package hellopaths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloPathsController {
    @GetMapping("/hello")
    @ResponseBody
    String hello() {
        return "Hello";
    }
    @GetMapping("/paths")
    @ResponseBody
    String paths() {
        return "Paths";
    }

}
