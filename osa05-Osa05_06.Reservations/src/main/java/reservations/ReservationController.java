package reservations;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {
    
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @PostMapping("/reservations")
    public String addReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationTo) {
        
        System.out.println("AA");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.isAuthenticated()) {
            return "redirect:/login";
        }
        
        List<Reservation> all = reservationRepository.findAll();
        for (Reservation reservation : all) {
            if ((reservation.getReservationFrom().isBefore(reservationTo) && reservation.getReservationTo().isAfter(reservationTo)) ||
                (reservation.getReservationFrom().isBefore(reservationFrom) && reservation.getReservationTo().isAfter(reservationFrom)))
                    return "redirect:/reservations";
        }
        
        Account acc = accountRepository.findByUsername(auth.getName());
        Reservation res = new Reservation(acc, reservationFrom, reservationTo);
        reservationRepository.save(res);
        return "redirect:/reservations";
    }
    
    @GetMapping("/reservations")
    public String reservations(Model model) {
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservations";
    }

}
