package project.vendingmachine.api;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.vendingmachine.data_model.Item;
import project.vendingmachine.services.ItemService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>User API controller</b>
 *
 */
@RestController
@RequestMapping("api")
public class ApiController {
    private final ItemService itemService;

    public ApiController(@Autowired ItemService itemService) {
        this.itemService = itemService;
    }


    /**
     * <b>api/getlist</b><br>
     * Api for getting available item list.<br>
     * @return
     */
    @JsonView(ApiController.class)
    @GetMapping("/getlist")
    public List<Item> getList(){
        return itemService.getItems();
    }

    /**
     * <b>api/deposit</b>
     * api for adding Item to vending machine
     * expects:<br>
     * <p>
     *     {
     *          type:string /required
     *     }
     * </p>
     * @param item
     * @return
     * @throws Exception
     */
    @PostMapping("/deposit")
    public Map<String, Object> deposit(@RequestBody @Valid Item item) throws Exception {
        int id = itemService.depositItem(item.getType());
        Map<String,Object> response = new HashMap<>();
        response.put("id", id);
        response.put("status", "OK");
        return response;
    }

    /**
     * @apiNote  <b>api/withdraw</b>
     * api for getting item from vending machine<br>
     * expects:<br>
     * <p>
     *   {
     *    type:string /required
     *   }
     * </p>
     * @param item
     * @return
     * @throws Exception
     */
    @PostMapping("/withdraw")
    public Map<String,String> withdraw(@RequestBody @Valid Item item) throws Exception {
        itemService.withdraw(item.getType());
        Map<String,String> result = new HashMap<>();
        result.put("status", "OK");
        return result;
    }

}
