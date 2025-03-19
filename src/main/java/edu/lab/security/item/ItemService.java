package edu.lab.security.item;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/*
  @author   kosta
  @project   security
  @class  ItemService
  @version  1.0.0 
  @since 20.03.2025 - 00.16
*/
@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private List<Item> items;

    @PostConstruct
    void init() {
        items.add(new Item("1", "name1", "description"));
        items.add(new Item("2", "name2", "description2"));
        items.add(new Item("3", "name3", "description3"));
        itemRepository.saveAll(items);
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getById(String id) {
        return itemRepository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        itemRepository.deleteById(id);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }
}
