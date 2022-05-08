
package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfiguration;
import acme.entities.toolkits.Item;
import acme.entities.toolkits.ItemToolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository {

	@Query("select i from Item i where i.inventor.id = :inventorId and i.itemType = 'COMPONENT'")
	Collection<Item> findAllComponentsFromInventor(int inventorId);

	@Query("select i from Item i where i.itemType = 'TOOL' and i.inventor.id = :inventorId")
	Collection<Item> findAllToolsFromInventor(int inventorId);

	@Query("select i from Item i where i.id = :id and i.inventor.id = :inventorId")
	Item findOneItemByIdFromInventor(int id, int inventorId);

	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select sum(it.quantity * i.retailPrice.amount) from Item i left join ItemToolkit it on i.id=it.item.id where it.toolkit.id = :id and i.retailPrice.currency=:currency")
	Double getItemPricesByIdAndCurrency(int id, String currency);
	
	@Query("select t from Item t where t.code=:code")
	Item getItemByCode(String code);

	@Query("select it from ItemToolkit it where it.item.id=:id")
    Collection<ItemToolkit> findItemToolkitByItemId(int id);
	
	

}
