package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Chimpum;
import acme.entities.toolkits.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository {
	
	//TODO
	@Query("select c from Chimpum c where c.item.itemType='TOOL' and c.item.inventor.id =:id")
	Collection<Chimpum> findManyChimpumByInventorId(int id);
	

	@Query("select c from Chimpum c where c.id=:id")
	Chimpum findOneChimpumById(int id);

	@Query("select i from Item i where i.itemType='TOOL' and i.inventor.id =:id")
	Collection<Item> findAllToolsByInventor(int id);

	
	@Query("select i from Item i where i.id = :id")
	Item findOneItemById(int id);
	
	@Query("select c from Chimpum c where c.item.id = :id")
	Chimpum findOneChimpumItemById(int id);
	
	
	
	

}
