package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfiguration;
import acme.entities.toolkits.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {

	@Query("select t from Toolkit t")
	Collection<Toolkit> findAllToolkits() ;
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(int id);


	@Query("select sum(it.quantity * i.retailPrice.amount) from Item i left join ItemToolkit it on i.id=it.item.id where it.toolkit.id = :id")
	Double getToolkitPriceById(int id);

	@Query("select it.item from ItemToolkit it where it.toolkit.id=:id and it.item.itemType='COMPONENT'")
	Collection<Item> getComponentsFromToolkit(int id);
	
	@Query("select it.item from ItemToolkit it where it.toolkit.id=:id and it.item.itemType='TOOL'")
	Collection<Item> getToolsFromToolkit(int id);

	@Query("select t from Toolkit t where t.id=:id and t.inventor.id=:inventorId")
	Toolkit findOneToolkitByIdFromInventor(int id, int inventorId);

	@Query("select t from Toolkit t where t.inventor.id=:inventorId")
	Collection<Toolkit> findAllToolkitsByInventor(int inventorId);
	
	@Query("select sum(it.quantity * i.retailPrice.amount) from Item i left join ItemToolkit it on i.id=it.item.id where it.toolkit.id = :id and i.retailPrice.currency=:currency")
	Double getToolkitPricesByIdAndCurrency(int id, String currency);
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
}
