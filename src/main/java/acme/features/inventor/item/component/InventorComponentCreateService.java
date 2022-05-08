package acme.features.inventor.item.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.Item;
import acme.entities.toolkits.ItemType;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;


@Controller
public class InventorComponentCreateService implements AbstractCreateService<Inventor, Item>{

	@Autowired
	protected InventorItemRepository repository;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		return true;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "name", "technology", "description", "retailPrice", "info");
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		request.unbind(entity, model, "code", "name", "technology", "description", "retailPrice", "info");
		model.setAttribute("readonly", false);
	}

	@Override
	public Item instantiate(final Request<Item> request) {
		assert request != null;
		final Item entity = new Item();
		//TODO Created in draftmode, not published.
		
		entity.setDescription("");
		entity.setName("");
		entity.setTechnology("");
		entity.setItemType(ItemType.COMPONENT);
		return entity;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		
	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		entity.setItemType(ItemType.COMPONENT);
		entity.setInventor(this.repository.findOneInventorById(request.getPrincipal().getActiveRoleId()));
		this.repository.save(entity);
	}

}
