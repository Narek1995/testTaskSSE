package project.vendingmachine.services;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import project.vendingmachine.data_model.Item;
import project.vendingmachine.data_model.ItemRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemServiceTest {
    private ItemService itemService;
    private ItemRepository itemRepository;
    private Item item;
    private List<Item> items;

    @Before
    private void setUp(){
        Item item = mock(Item.class);
        items = new ArrayList<>();
        items.add(item);
        this.item = item;
        itemRepository = mock(ItemRepository.class);
        when(itemRepository.save(item)).thenReturn(item);
        when(itemRepository.findAll()).thenReturn(items);
        when(itemRepository.getByType(item.getType())).thenReturn(item);
        doNothing().when(spy(itemRepository)).delete(item);

    }
}
