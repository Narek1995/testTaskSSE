package project.vendingmachine.services;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import project.vendingmachine.data_model.Item;
import project.vendingmachine.data_model.ItemRepository;

import java.util.List;

public class ItemService {
    private @Autowired ItemRepository itemRepository;


    public Item saveOrUpdateItem(Item item){
        return itemRepository.save(item);
    }

    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    public void deleteItem(String type){
        itemRepository.deleteByType(type);
    }

    public Item getItemByType(String type){
        return itemRepository.getByType(type);
    }

    public int depositItem(String type) throws Exception {
        Item item = getItemByType(type);
        if(item != null){
            if(item.getCount() < item.getMaxCount()){
                item.setCount(item.getCount() + 1);
                saveOrUpdateItem(item);
                return item.getId();
            }else {
                throw new Exception();
            }
        }else {
            throw new Exception();
        }
    }

    public void withdraw(String type) throws Exception {
        Item item = getItemByType(type);
        if(item != null){
            if(item.getCount() > 0){
                item.setCount(item.getCount() - 1);
                saveOrUpdateItem(item);
            }else {
                throw new Exception();
            }
        }else {
            throw new Exception();
        }
    }
}
