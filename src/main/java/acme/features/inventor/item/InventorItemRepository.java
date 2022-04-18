package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository{
	
	@Query("select i from Item i where i.inventor.id = :inventorId and i.itemType = 'COMPONENT'")
	Collection<Item> findAllComponentsFromInventor(int inventorId);
	
	@Query("select i from Item i where i.itemType = 'TOOL' and i.inventor.id = :inventorId")
	Collection<Item> findAllToolsFromInventor(int inventorId);
	
	@Query("select i from Item i where i.id = :id and i.inventor.id = :inventorId")
	Item findOneItemByIdFromInventor(int id, int inventorId);
	
}
