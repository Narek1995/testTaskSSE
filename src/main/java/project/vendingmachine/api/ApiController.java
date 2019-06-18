package project.vendingmachine.api;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import project.vendingmachine.data_model.Item;
import project.vendingmachine.services.ItemService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class ApiController {
    @Autowired
    private ItemService itemService;


    @GetMapping("/get_items")
    public List<Item> getItems(){
        return itemService.getItems();
    }

    @PostMapping("/deposit")
    public JSONObject deposit(@Valid Item item) throws Exception {
        int id = itemService.depositItem(item.getType());
        JSONObject response = new JSONObject();
        response.put("id", id);
        response.put("status", "OK");
        return response;
    }

    @PostMapping("/withdraw")
    public JSONObject withdraw(@Valid Item item) throws Exception {
        itemService.withdraw(item.getType());
        JSONObject response = new JSONObject();
        response.put("status", "OK");
        return response;
    }

}
