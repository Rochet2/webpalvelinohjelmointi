package euroshopper;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShoppingCartController {
    
    @Autowired
    ShoppingCart shoppingCart;
    
    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/cart")
    public String cart(Model model) {
        Map<Item, Long> items = shoppingCart.getItems();
        model.addAttribute("items", items);
        model.addAttribute("sum", items.values().stream().mapToLong(Long::longValue).sum());
        return "cart";
    }

    @PostMapping("/cart/items/{id}")
    public String cart(@PathVariable Long id) {
        Item item = itemRepository.getOne(id);
        shoppingCart.addToCart(item);
        return "redirect:/cart";
    }
}
