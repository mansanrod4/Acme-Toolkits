package acme.features.inventor.item;

import acme.entities.toolkits.Item;
import acme.entities.toolkits.ItemType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;

public class InventorItemUtils {
	
	
	private InventorItemUtils() {
	}
	
	public static Boolean authoriseInventor(final Request<Item> request, final InventorItemRepository repository) {
		assert request!=null;
		final int inventorId = request.getPrincipal().getActiveRoleId(); 	
		final int id = request.getModel().getInteger("id");
		final Item item = repository.findOneItemById(id);
		return (inventorId == item.getInventor().getId());
	}
	
	public static void unbindItem(final Request<Item> request, final Item entity, final Model model) {
		request.unbind(entity, model, "code", "name", "technology", "description", "retailPrice", "info", "published");
	}
	
	public static void bindItem(final Request<Item> request, final Item entity, final Errors errors) {
		request.bind(entity, errors, "code", "name", "technology", "description", "retailPrice", "info");
	}
	
	public static Item instantiateItem(final Request<Item> request, final InventorItemRepository repository, final ItemType itemType) {
		final Item entity = new Item();
		
		entity.setDescription("");
		entity.setName("");
		entity.setTechnology("");
		entity.setItemType(itemType);
		entity.setPublished(false);
		entity.setInventor(repository.findOneInventorById(request.getPrincipal().getActiveRoleId()));
		
		return entity;
	}
}
