package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorItemUpdateService implements AbstractUpdateService<Inventor, Item>{

	@Autowired
	protected InventorItemRepository repository;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request!=null;
		final int inventorId = request.getPrincipal().getActiveRoleId(); 	
		final int id = request.getModel().getInteger("id");
		final Item item = this.repository.findOneItemById(id);
		return (inventorId == item.getInventor().getId()); 
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
		model.setAttribute("readonly", false);
		request.unbind(entity, model, "code", "name", "technology", "description", "retailPrice", "info", "published");
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		
		final Integer id = request.getModel().getInteger("id");
		return this.repository.findOneItemById(id);
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			final Item existing=this.repository.getItemByCode(entity.getCode());
			errors.state(request, existing == null ||  entity.getId() == existing.getId(), "code", "inventor.item.form.error.duplicated");
		}
		
		if (!errors.hasErrors("retailPrice")) {
			errors.state(request, entity.getRetailPrice().getAmount()>0, "retailPrice", "inventor.item.form.error.retailPrice.negativeOrZero");
		}
	}

	@Override
	public void update(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
