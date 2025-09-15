// =================================================================
// LAGER 2: REPOSITORY LAYER (Data Access)
// =================================================================

package se.hatice.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.hatice.backend.entity.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}