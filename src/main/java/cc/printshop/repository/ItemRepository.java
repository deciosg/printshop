package cc.printshop.repository;

import org.springframework.data.repository.CrudRepository;

import cc.printshop.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
