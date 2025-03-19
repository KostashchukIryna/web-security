package edu.lab.security.item;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/*
  @author   kosta
  @project   security
  @class  ItemRestController
  @version  1.0.0 
  @since 20.03.2025 - 00.17
*/

@RestController
@RequestMapping("/api/v1/items")
@AllArgsConstructor
public class ItemRestController {
    private final ItemService service;

    @GetMapping
    public List<Item> getItems() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Item getOneItem(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        service.deleteById(id);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return service.createItem(item);
    }

    @PutMapping
    public Item updateItem(@RequestBody Item item) {
        return service.updateItem(item);
    }
}
