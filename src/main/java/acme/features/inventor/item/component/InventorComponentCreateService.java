package acme.features.inventor.item.component;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.toolkits.Item;
import acme.features.inventor.item.InventorItemRepository;
import acme.features.inventor.item.InventorItemUtils;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

public class InventorComponentCreateService implements AbstractCreateService<Inventor, Item>{

	@Autowired
	protected InventorItemRepository repository;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		return InventorItemUtils.authoriseInventor(request, null);
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		entity.setCode(request.getModel().getAttribute("code").toString());
		entity.setDescription(request.getModel().getString("description"));
		entity.setInfo(request.getModel().getString("info"));
		//TODO aun queda aqui el resto de attrs
		
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item instantiate(final Request<Item> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
		// TODO Auto-generated method stub
		
	}

}
