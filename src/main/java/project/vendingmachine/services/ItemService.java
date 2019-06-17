package project.vendingmachine.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import project.vendingmachine.data_model.Item;
import project.vendingmachine.data_model.ItemRepository;

import java.util.List;

public class ItemService {
    private @Autowired ItemRepository itemRepository;


    public Item addItem(Item item){
        return itemRepository.save(item);
    }

    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    public void deleteItem(String name){
        itemRepository.deleteByName(name);
    }
}
