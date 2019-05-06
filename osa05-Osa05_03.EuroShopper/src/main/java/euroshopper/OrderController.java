package euroshopper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    ShoppingCart shoppingCart;

    @RequestMapping("/orders")
    public String list(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    @PostMapping("/orders")
    public String order(@RequestParam String name, @RequestParam String address) {

        Order order = new Order();
        order.setName(name);
        order.setAddress(address);
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        Map<Item, Long> items = shoppingCart.getItems();
        for (Entry<Item, Long> item : items.entrySet()) {
            orderItems.add(new OrderItem(item.getKey(), item.getValue()));
        }
        order.setOrderItems(orderItems);

        orderRepository.save(order);
        
        shoppingCart.setItems(new HashMap<>());

        return "redirect:/orders";
    }
}
