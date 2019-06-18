package project.vendingmachine.data_model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository <Item, Integer> {
    Item getByType(String name);
}
