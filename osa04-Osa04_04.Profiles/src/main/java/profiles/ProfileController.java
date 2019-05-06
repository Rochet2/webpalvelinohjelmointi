package profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {

    @Value("${spring.profiles.active:production}")
    private String activeProfile;

    @ResponseBody
    @GetMapping("/profile")
    public String getProfile() {
        return activeProfile;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("profile", activeProfile);
        return "index";
    }

}
