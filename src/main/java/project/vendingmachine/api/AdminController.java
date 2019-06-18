package project.vendingmachine.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.vendingmachine.data_model.Item;
import project.vendingmachine.exceptions.RequestProcessingError;
import project.vendingmachine.services.ItemService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/addOrUpdateItem")
    public Map<String, Object> addItem(@RequestBody @Valid Item item){
       Map<String, Object> result = new HashMap<>();
        item = itemService.saveOrUpdateItem(item);
        result.put("status", "OK");
        result.put("item", item);
        return result;
    }


    @PostMapping("/deletItem")
    public Pair<String,String> deleteItem(@RequestBody @Valid Item item) throws RequestProcessingError {
        itemService.deleteItem(item.getType());
        return new Pair<>("status", "OK");
    }

    @GetMapping("/getAllItems")
    public List<Item> getItems(){
        return itemService.getItems();
    }
}
