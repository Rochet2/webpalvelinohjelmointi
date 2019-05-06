package gifbin;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DefaultController {

    @Autowired
    GifRepository gifRepository;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/gifs";
    }

    @GetMapping("/gifs")
    public String redirect2() {
        return "redirect:/gifs/1";
    }

    @PostMapping(path = "/gifs")
    public String newgif(@RequestParam("file") MultipartFile file) throws IOException {
        if ("image/gif".equals(file.getContentType())) {
            Gif g = new Gif();
            g.setContent(file.getBytes());
            gifRepository.save(g);
        }
        return "redirect:/gifs";
    }

    @GetMapping("/gifs/{id}")
    public String gifs(Model model, @PathVariable Long id) {
        long count = gifRepository.count();
        model.addAttribute("count", count);
        if (id >= 1 && id < count) {
            model.addAttribute("next", id + 1);
        }
        if (id > 1 && id <= count) {
            model.addAttribute("previous", id - 1);
        }
        if (id >= 1 && id <= count) {
            model.addAttribute("current", id);
        }
        return "gifs";
    }

    @GetMapping(path = "/gifs/{id}/content", produces = "image/gif")
    @ResponseBody
    public byte[] content(@PathVariable Long id) {
        return gifRepository.getOne(id).getContent();
    }
}
