package persondatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonDatabaseController {

    @Autowired
    public PersonRepository repo;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("persons", repo.findAll());
        return "index";
    }
    
    @PostMapping("/")
    public String home2(@RequestParam String name) {
        repo.save(new Person(name));
        return "redirect:/";
    }

}
