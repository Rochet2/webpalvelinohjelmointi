package reloadheroes;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReloadController {

    @Autowired
    private ReloadStatusRepository reloadStatusRepository;

    @Autowired
    private HttpSession session;

    @RequestMapping("*")
    public String reload(Model model) {
        ReloadStatus status = null;
        if (session.getAttribute("relid") != null) {
            status = reloadStatusRepository.getOne((long)session.getAttribute("relid"));
            status.setReloads(status.getReloads()+1);
            reloadStatusRepository.save(status);
        } else {
            status = new ReloadStatus();
            String name = RandomStringUtils.randomAlphanumeric(10);
            status.setName(name);
            status.setReloads(1);
            session.setAttribute("relid", reloadStatusRepository.save(status).getId());
        }
        model.addAttribute("status", status);
        List<ReloadStatus> scores = reloadStatusRepository.findAllByOrderByReloadsDesc(PageRequest.of(0, 5));
        model.addAttribute("scores", scores);
        return "index";
    }
}
