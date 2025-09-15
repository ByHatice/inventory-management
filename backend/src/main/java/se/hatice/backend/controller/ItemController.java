// =================================================================
// CONTROLLER LAYER (Web/API)
// =================================================================

package se.hatice.backend.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import se.hatice.backend.entity.Item;
import se.hatice.backend.service.ItemService;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public Item createItem(@Valid @RequestBody Item item) {
        return itemService.createItem(item);
    }

    @GetMapping("/all")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/item")
    public Item getItemById(@RequestParam Long id) {
        return itemService.getItemById(id);
    }

    @GetMapping("/search")
    public List<Item> searchItems(@RequestParam String searchTerm) {
        return itemService.searchItems(searchTerm);
    }

    @GetMapping("/low-stock")
    public List<Item> getLowStockItems(@RequestParam(defaultValue = "10") Integer threshold) {
        return itemService.getAllItems().stream()
                .filter(item -> item.getQuantity() < threshold)
                .toList();
    }

    @PutMapping("/update")
    public Item updateQuantity(@RequestParam Long id, Integer quantity) {
        return itemService.updateQuantity(id, quantity);
    }

    @DeleteMapping("/delete")
    public void deleteItem(@RequestParam Long id) {
        itemService.deleteItem(id);
    }
}
