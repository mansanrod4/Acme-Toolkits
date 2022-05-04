package acme.features.inventor.item;

import acme.entities.toolkits.Item;
import acme.framework.controllers.Request;

public class InventorItemUtils {
	
	
	private InventorItemUtils() {
	}
	
	public static Boolean authoriseInventor(final Request<Item> request, final InventorItemRepository repository) {
		assert request!=null;
		final int inventorId = request.getPrincipal().getActiveRoleId(); 	
		final int id = request.getModel().getInteger("id");
		final Item item = repository.findOneItemByIdFromInventor(id, inventorId);
		return (inventorId == item.getInventor().getId());
	}
}
