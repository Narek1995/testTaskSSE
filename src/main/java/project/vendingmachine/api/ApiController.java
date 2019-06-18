package project.vendingmachine.api;

import com.fasterxml.jackson.annotation.JsonView;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.vendingmachine.data_model.Item;
import project.vendingmachine.services.ItemService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")

public class ApiController {
    private final ItemService itemService;

    public ApiController(@Autowired ItemService itemService) {
        this.itemService = itemService;
    }


    @JsonView(ApiController.class)
    @GetMapping("/getlist")
    public List<Item> getItems(){
        return itemService.getItems();
    }

    @PostMapping("/deposit")
    public Map<String, Object> deposit(@Valid Item item) throws Exception {
        int id = itemService.depositItem(item.getType());
        Map<String,Object> response = new HashMap<>();
        response.put("id", id);
        response.put("status", "OK");
        return response;
    }

    @PostMapping("/withdraw")
    public Pair<String, String> withdraw(@Valid Item item) throws Exception {
        itemService.withdraw(item.getType());
        return new Pair<>("status", "OK");
    }

}
