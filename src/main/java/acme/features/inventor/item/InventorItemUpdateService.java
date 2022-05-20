package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.entities.toolkits.Item;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import notenoughspam.SpamDetector;

@Service
public class InventorItemUpdateService implements AbstractUpdateService<Inventor, Item>{

	@Autowired
	protected InventorItemRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository systemConfigRepository;
	
	
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
		
final SystemConfiguration sysConfig = this.systemConfigRepository.findSystemConfiguration();
		
		final Double strongSpamThreshold = sysConfig.getStrongSpamThreshold();
		final String[] strongSpamWords = sysConfig.getStrongSpamTerms().split(",");
		
		final Double weakSpamThreshold = sysConfig.getWeakSpamThreshold();
		final String[] weakSpamWords = sysConfig.getWeakSpamTerms().split(",");
		
		final SpamDetector spamDetector = new SpamDetector(strongSpamThreshold, weakSpamThreshold, strongSpamWords, weakSpamWords);
		
		
		if (!errors.hasErrors("name")) {
			final String name = entity.getName();
			errors.state(request, spamDetector.stringHasSpam(name), "name", "inventor.item.form.error.spam");
		}
		
		if (!errors.hasErrors("technology")) {
			errors.state(request, spamDetector.stringHasSpam(entity.getDescription()), "technology", "inventor.item.form.error.spam");
		}
		
		if (!errors.hasErrors("description")) {
			errors.state(request, spamDetector.stringHasSpam(entity.getDescription()), "description", "inventor.item.form.error.spam");
		}
		
		if (!errors.hasErrors("retailPrice")) {
			final String currency = entity.getRetailPrice().getCurrency();
			final boolean currencyIsSuported = this.systemConfigRepository.findSystemConfiguration().getAcceptedCurrencies().contains(currency);
			errors.state(request, currencyIsSuported, "retailPrice", "inventor.item.form.error.retailPrice.currency-not-supported");
			
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
