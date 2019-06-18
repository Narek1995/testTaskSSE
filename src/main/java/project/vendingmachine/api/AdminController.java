package project.vendingmachine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.vendingmachine.data_model.Item;
import project.vendingmachine.services.ItemService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/add_item")
    public Item addItem(@RequestBody @Valid Item item){
        return itemService.saveOrUpdateItem(item);
    }


    @PostMapping("/delete_item")
    public String deleteItem(@RequestBody @Valid Item item){
        itemService.deleteItem(item.getType());
        return "OK";
    }
}
