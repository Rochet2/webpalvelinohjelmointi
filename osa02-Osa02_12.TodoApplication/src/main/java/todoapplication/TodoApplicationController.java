package todoapplication;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoApplicationController {
    @Autowired
    ItemRepository repo;

    @GetMapping("/")
    String home(Model model) {
        model.addAttribute("items", repo.findAll());
        return "index";
    }

    @PostMapping("/")
    String home(@RequestParam String name) {
        repo.save(new Item(name, 0));
        return "redirect:/";
    }

    @GetMapping("/{id}")
    String todo(Model model, @PathVariable Long id) {
        Item item = repo.getOne(id);
        item.setChecked(item.getChecked()+1);
        repo.save(item);
        model.addAttribute("item", item);
        return "todo";
    }
}
