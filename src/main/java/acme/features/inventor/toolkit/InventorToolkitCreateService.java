package acme.features.inventor.toolkit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.entities.toolkits.Item;
import acme.entities.toolkits.Toolkit;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor, Toolkit>{

	// Internal state --------------------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	
	// AbstractCreateService<Inventor, Toolkit> interface --------------------------------

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;
		
		final Toolkit toolkit= new Toolkit();
		final Inventor inventor = this.repository.findInventorById(request.getPrincipal().getActiveRoleId());
		
		toolkit.setCode("");
		toolkit.setTitle("");
		toolkit.setAssemblyNotes("");
		
		toolkit.setPublished(false);
		toolkit.setInventor(inventor);
		return toolkit;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "assemblyNotes","description", "info");
		
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

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "info", "published");

		final MoneyExchange mE = new MoneyExchange();
		final List<Money> pricesFix = mE.convertMoney(prices, sc.getSystemCurrency());

		final Money money = new Money();
		final Double amount = pricesFix.stream().mapToDouble(Money::getAmount).sum();
		money.setAmount(amount);
		money.setCurrency(sc.getSystemCurrency());

		model.setAttribute("price", money);
		model.setAttribute("inventor", entity.getInventor().getIdentity().getFullName());
	}


	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		if(!errors.hasErrors("code")) {
			final Toolkit existing=this.repository.findOneToolkitByCode(entity.getCode());
			errors.state(request, existing==null, "code", "inventor.toolkit.form.error.duplicated-code");
		}
		
		if(!errors.hasErrors("code")) {
			final Item existing=this.repository.findOneItemByCode(entity.getCode());
			errors.state(request, existing==null, "code", "inventor.toolkit.form.error.duplicated-code");
		}
		
	}

	@Override
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
}