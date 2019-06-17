package project.vendingmachine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.vendingmachine.data_model.Item;
import project.vendingmachine.services.ItemService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired ItemService itemService;

    @PostMapping("/add_item")
    public Item addItem(@RequestBody @Valid Item item){
        return itemService.addItem(item);
    }

    @GetMapping("/get_items")
    public List<Item> getItems(){
        return itemService.getItems();
    }

    @DeleteMapping("/delete_item")
    public String deleteItem(@RequestBody @Valid Item item){
        itemService.deleteItem(item.getName());
        return "OK";
    }
}
