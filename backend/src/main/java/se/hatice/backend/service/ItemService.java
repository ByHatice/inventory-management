// =================================================================
// LAGER 3: SERVICE LAYER (Business Logic)
// =================================================================

package se.hatice.backend.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.hatice.backend.entity.Item;
import se.hatice.backend.exception.InvalidQuantityException;
import se.hatice.backend.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> searchItems(String searchTerm) {
        return itemRepository.findByNameContainingIgnoreCase(searchTerm);
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artikel med id " + id + " hittades inte"));
    }

    @Transactional
    public Item updateQuantity(Long id, Integer newQuantity) {
        if (newQuantity < 0) {
            throw new InvalidQuantityException("Antal får inte vara negativt");
        }
        Item item = getItemById(id);
        item.setQuantity(newQuantity);
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        Item item = getItemById(id);
        itemRepository.delete(item);
    }
}
