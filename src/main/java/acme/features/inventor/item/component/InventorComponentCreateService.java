package acme.features.inventor.item.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Item;
import acme.entities.toolkits.ItemType;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;


@Service
public class InventorComponentCreateService implements AbstractCreateService<Inventor, Item>{

	@Autowired
	protected InventorItemRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository systemConfigRepository;
	
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
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "technology", "description", "retailPrice", "info", "published");
	}

	@Override
	public Item instantiate(final Request<Item> request) {
		assert request != null;
		final Item entity = new Item();
		
		entity.setDescription("");
		entity.setName("");
		entity.setTechnology("");
		entity.setItemType(ItemType.COMPONENT);
		entity.setPublished(false);
		entity.setInventor(this.repository.findOneInventorById(request.getPrincipal().getActiveRoleId()));
		
		return entity;
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
			
			final String currency = entity.getRetailPrice().getCurrency();
			final boolean currencyIsSuported = this.systemConfigRepository.findSystemConfiguration().getAcceptedCurrencies().contains(currency);
			errors.state(request, currencyIsSuported, "retailPrice", "inventor.item.form.error.retailPrice.currency-not-supported");
			
			errors.state(request, entity.getRetailPrice().getAmount()>0, "retailPrice", "inventor.item.form.error.retailPrice.negativeOrZero");
		}
		
	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}