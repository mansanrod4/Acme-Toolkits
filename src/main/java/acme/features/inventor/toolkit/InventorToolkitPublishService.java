package acme.features.inventor.toolkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.entities.toolkits.Item;
import acme.entities.toolkits.ItemType;
import acme.entities.toolkits.Toolkit;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor, Toolkit>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	
	// AbstractUpdateService<Inventor, Toolkit> interface -------------------------
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final int inventorId = request.getPrincipal().getActiveRoleId();
		final Toolkit toolkit = this.repository.findOneToolkitById(id);
		
		return (toolkit.getInventor().getId()==inventorId && !toolkit.isPublished());
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final List<Money> prices = new ArrayList<>();
		final SystemConfiguration sc = this.repository.findSystemConfiguration();
		for (final String curr : sc.getAcceptedCurrencies().trim().split(",")) {
			final Money price = new Money();
			price.setCurrency(curr);
			price.setAmount(this.repository.getToolkitPricesByIdAndCurrency(entity.getId(), curr));
			prices.add(price);
		}

		request.unbind(entity, model, "title", "description", "assemblyNotes", "info", "published");

		final MoneyExchange mE = new MoneyExchange();
		final List<Money> pricesFix = mE.convertMoney(prices, sc.getSystemCurrency());

		final Money money = new Money();
		final Double amount = pricesFix.stream().mapToDouble(Money::getAmount).sum();
		money.setAmount(amount);
		money.setCurrency(sc.getSystemCurrency());

		model.setAttribute("price", money);
		model.setAttribute("inventor", entity.getInventor().getIdentity().getFullName());}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		int id;
		Toolkit toolkit;
		id = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(id);
		return toolkit;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("*")) {
			
			final Collection<Item> tool=this.repository.findItemByTypeFromToolkit(entity.getId(), ItemType.TOOL);
			errors.state(request, tool.size()==1,"*", "inventor.toolkit.no-tool-in-toolkit");
		}
		
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		entity.setPublished(true);
		this.repository.save(entity);
		
	}

}
