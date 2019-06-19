package project.vendingmachine.api;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import project.vendingmachine.data_model.Item;
import project.vendingmachine.data_model.RequestError;
import project.vendingmachine.exceptions.RequestProcessingError;
import project.vendingmachine.services.ItemService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@WebMvcTest(value = AdminController.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ItemService itemService;


    private Item item;
    private List<Item> items ;
    @Before
    public void setUp() {
        Item item = new Item();
        item.setType("test");
        item.setMaxCount(10);
        item.setCount(0);
        item.setId(16);
        items = new ArrayList<>();
        items.add(item);
        this.item = item;
        when(itemService.getItems()).thenReturn(items);
        when(itemService.saveOrUpdateItem(item)).thenReturn(item);

    }


    @Test
    public void getAllItemsTest() throws Exception {
        mockMvc.perform(get("/admin/getAllItems"))
                .andExpect(status().isOk()) //line C
                .andExpect(content().json("[{\"id\":16,\"type\":\"test\",\"maxCount\":10,\"count\":0}]")) ;
    }

    @Test
    public void deleteItemTest() throws Exception {
        doNothing().when(itemService).deleteItem(item.getType());
        mockMvc.perform(post("/admin/deleteItemType")
                .contentType("application/json;charset=UTF-8")
                .content("{\"type\":\"test\"}"))
                .andExpect(status().isOk()) //line C
                .andExpect(content().json("{\"status\":\"OK\"}")) ;
        doThrow(new RequestProcessingError(RequestError.ITEM_DOES_NOT_EXISTS_ERROR)).when(itemService).deleteItem(item.getType());
        mockMvc.perform(post("/admin/deleteItemType")
                .contentType("application/json;charset=UTF-8")
                .content("{\"type\":\"test\"}"))
                .andExpect(status().isOk()) //line C
                .andExpect(content().json("{\"status\":\"Item Does not exists\"}")) ;

    }

}
