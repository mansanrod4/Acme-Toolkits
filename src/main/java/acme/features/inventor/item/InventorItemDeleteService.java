package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorItemDeleteService implements AbstractDeleteService<Inventor, Item>{

	@Autowired
	protected InventorItemRepository inventorItemRepository;
	
	@Override
	public boolean authorise(final Request<Item> request) {

		assert request != null;
		boolean res;
		int inventorId;
		int id;
		Item item;
		Inventor inventor;

		inventorId = request.getPrincipal().getActiveRoleId();
		id = request.getModel().getInteger("id");
		item = this.inventorItemRepository.findOneItemById(id);
		inventor = item.getInventor();

		res = item.isDraftMode() && request.isPrincipal(inventor);

		return res;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		InventorItemUtils.bindItem(request, entity, errors);
		
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		model.setAttribute("readonly", false);
		InventorItemUtils.unbindItem(request, entity, model);
		
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		
		final Integer id = request.getModel().getInteger("id");
		return this.inventorItemRepository.findOneItemById(id);
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		
		this.inventorItemRepository.delete(entity);
		
	}

}

