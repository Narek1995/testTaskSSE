package project.vendingmachine.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.vendingmachine.data_model.Item;
import project.vendingmachine.exceptions.RequestProcessingError;
import project.vendingmachine.services.ItemService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>Administrative API controller</b>
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ItemService itemService;

    public AdminController(@Autowired ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * <b>/admin/addOrUpdateItemType</b>
     * API for adding or updating Item types<br>
     * expects:<br>
     * <p>
     *     {
     *           type: string, /required
     *           id: integer /not required
     *           count: integer /default value 0
     *           maxCount: integer /default value 10
     *      }
     * </p>
     * @param item
     * @return
     */
    @PostMapping("/addOrUpdateItemType")
    public Map<String, Object> addOrUpdateItem(@RequestBody @Valid Item item){
       Map<String, Object> result = new HashMap<>();
        item = itemService.saveOrUpdateItem(item);
        result.put("status", "OK");
        result.put("item", item);
        return result;
    }


    /**
     * <b>/admin/deleteItemType</b>
     * Api to delete item type from vending machine.<br>
     * expects:<br>
     * <p>
     *     {
     *          type:string /required
     *     }
     * </p>
     * @param item
     * @return
     * @throws RequestProcessingError
     */
    @PostMapping("/deleteItemType")
    public Map<String,String> deleteItem(@RequestBody @Valid Item item) throws RequestProcessingError {
        itemService.deleteItem(item.getType());
        Map<String, String> result = new HashMap<>();
        result.put("status", "OK");
        return result;
    }

    /**
     * <b>/admin/getAllItems</b>
     * Get information about all Item types.<br>
     *
     * @return
     */
    @GetMapping("/getAllItems")
    public List<Item> getItems(){
        return itemService.getItems();
    }
}
