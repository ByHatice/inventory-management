// =================================================================
// CONTROLLER LAYER (Web/API)
// =================================================================

package se.hatice.backend.controller;

import org.springframework.web.bind.annotation.*;
import se.hatice.backend.entity.Item;
import se.hatice.backend.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
@CrossOrigin
public class ItemController {

    private ItemService itemService;

    @PostMapping("/add")
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping
    public List<Item> searchItems(@RequestParam String searchTerm) {
        return itemService.searchItems(searchTerm);
    }

    @GetMapping
    public List<Item> getLowStockItems() {
        return itemService.getAllItems().stream()
                .filter(item -> item.getQuantity() < 5)
                .toList();
    }

    @PutMapping("/{id}/quantity")
    public Item updateQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        return itemService.updateQuantity(id, quantity);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}
