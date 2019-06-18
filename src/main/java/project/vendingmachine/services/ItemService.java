package project.vendingmachine.services;

import org.springframework.beans.factory.annotation.Autowired;
import project.vendingmachine.data_model.Item;
import project.vendingmachine.data_model.ItemRepository;
import project.vendingmachine.data_model.RequestError;
import project.vendingmachine.exceptions.RequestProcessingError;

import java.util.List;

public class ItemService {
    private ItemRepository itemRepository;

    public ItemService(@Autowired ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item saveOrUpdateItem(Item item){
        return itemRepository.save(item);
    }

    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    public void deleteItem(String type) throws RequestProcessingError {
       Item item = itemRepository.getByType(type);
       if(item != null){
           itemRepository.delete(item);
       }else {
           throw new RequestProcessingError(RequestError.ITEM_DOES_NOT_EXISTS_ERROR);
       }
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
                throw new RequestProcessingError(RequestError.VENDING_MACHINE_IS_FULL_ERROR);
            }
        }else {
            throw new RequestProcessingError(RequestError.ITEM_DOES_NOT_EXISTS_ERROR);
        }
    }

    public void withdraw(String type) throws Exception {
        Item item = getItemByType(type);
        if(item != null){
            if(item.getCount() > 0){
                item.setCount(item.getCount() - 1);
                saveOrUpdateItem(item);
            }else {
                throw new RequestProcessingError(RequestError.OUT_OF_ITEMS_ERROR);
            }
        }else {
            throw new RequestProcessingError(RequestError.ITEM_DOES_NOT_EXISTS_ERROR);
        }
    }
}
