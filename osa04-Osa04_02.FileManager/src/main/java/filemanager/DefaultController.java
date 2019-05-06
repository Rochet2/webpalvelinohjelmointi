package filemanager;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DefaultController {

    @Autowired
    FileWrapRepository fileWrapRepository;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/files";
    }

    @GetMapping("/files")
    public String files(Model model) {
        model.addAttribute("files", fileWrapRepository.findAll());
        return "files";
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> files(@PathVariable Long id) {
        FileWrap fw = fileWrapRepository.getOne(id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(fw.getContentLength());
        headers.setContentType(MediaType.parseMediaType(fw.getContentType()));
        headers.add("Content-Disposition", "attachment; filename=" + fw.getName());
        return new ResponseEntity<>(fw.getContent(), headers, HttpStatus.CREATED);
    }

    @PostMapping("/files")
    public String addfile(@RequestParam MultipartFile file) throws IOException {
        FileWrap f = new FileWrap();
        f.setContent(file.getBytes());
        f.setContentLength(file.getSize());
        f.setContentType(file.getContentType());
        f.setName(file.getOriginalFilename());
        fileWrapRepository.save(f);
        return "redirect:/files";
    }

    @DeleteMapping("/files/{id}")
    public String deletefile(@PathVariable Long id) {
        fileWrapRepository.deleteById(id);
        return "redirect:/files";
    }
}
