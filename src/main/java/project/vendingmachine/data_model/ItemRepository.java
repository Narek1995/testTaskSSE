package project.vendingmachine.data_model;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface ItemRepository extends JpaRepository <Item, Integer> {
    @Transactional
    void deleteByName(String name);
}
