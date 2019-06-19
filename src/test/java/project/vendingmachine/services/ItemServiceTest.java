package project.vendingmachine.services;

import org.junit.Before;
import org.junit.Test;
import project.vendingmachine.data_model.Item;
import project.vendingmachine.data_model.ItemRepository;
import project.vendingmachine.exceptions.RequestProcessingError;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class ItemServiceTest {
    private ItemService itemService;
    private ItemRepository itemRepository;
    private Item item;
    private List<Item> items ;

    @Before
    public void setUp(){
        Item item = mock(Item.class);
        items = new ArrayList<>();
        items.add(item);
        this.item = item;
        itemRepository = mock(ItemRepository.class);
        when(itemRepository.save(item)).thenReturn(item);
        when(itemRepository.findAll()).thenReturn(items);
        doNothing().when(itemRepository).delete(item);
        itemService = new ItemService(itemRepository);

    }

    @Test
    public void saveOrUpdateItemTest(){
        Item item = itemService.saveOrUpdateItem(this.item);
        assertEquals(item, this.item);
    }

    @Test
    public void getItemsTest(){
        List<Item> items = itemService.getItems();
        assertEquals(items, this.items);
    }

    @Test(expected = RequestProcessingError.class)
    public void deleteItemTest() throws RequestProcessingError {
        when(itemRepository.getByType(item.getType())).thenReturn(null);
        itemService.deleteItem(item.getType());
    }

    @Test
    public void getItemByType(){
        when(itemRepository.getByType(item.getType())).thenReturn(item);
        Item item = itemService.getItemByType(this.item.getType());
        assertEquals(item, this.item);
        when(itemRepository.getByType(item.getType())).thenReturn(null);
        item = itemService.getItemByType(this.item.getType());
        assertNull(item);
    }

    @Test
    public void depositItemTest() throws Exception {
        when(itemRepository.getByType(item.getType())).thenReturn(item);
        when(item.getMaxCount()).thenReturn(Integer.MAX_VALUE);
        when(item.getCount()).thenReturn(0);
        int id = itemService.depositItem(item.getType());
        assertEquals(id, item.getId());
    }

    @Test(expected = RequestProcessingError.class)
    public void depositWrongItemTest() throws Exception {
        when(itemRepository.getByType(item.getType())).thenReturn(null);
        int id = itemService.depositItem(item.getType());
    }

    @Test(expected = RequestProcessingError.class)
    public void depositLimitReachedTest() throws Exception {
        when(itemRepository.getByType(item.getType())).thenReturn(item);
        when(item.getMaxCount()).thenReturn(Integer.MAX_VALUE);
        when(item.getCount()).thenReturn(Integer.MAX_VALUE);
        int id = itemService.depositItem(item.getType());
    }

    @Test
    public void withdrawTest() throws RequestProcessingError {
        when(itemRepository.getByType(item.getType())).thenReturn(item);
        when(item.getCount()).thenReturn(1);
        itemService.withdraw(item.getType());
    }

    @Test(expected = RequestProcessingError.class)
    public void withdrawWrongItemTest() throws RequestProcessingError {
        when(itemRepository.getByType(item.getType())).thenReturn(null);
        itemService.withdraw(item.getType());
    }

    @Test(expected = RequestProcessingError.class)
    public void withdrawLimitReachedTest() throws RequestProcessingError {
        when(itemRepository.getByType(item.getType())).thenReturn(item);
        when(item.getCount()).thenReturn(0);
        itemService.withdraw(item.getType());
    }
}
