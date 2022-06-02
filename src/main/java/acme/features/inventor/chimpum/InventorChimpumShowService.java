
package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.entities.Chimpum;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor, Chimpum> {

	@Autowired
	protected InventorChimpumRepository						repository;

	@Autowired
	protected AdministratorSystemConfigurationRepository	repoSys;

	@Autowired
	protected AuthenticatedMoneyExchangePerformService		serviceMoney;


	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		boolean result;
		int chimpumId;
		Chimpum chimpum;
		int inventorId;

		chimpumId = request.getModel().getInteger("id");
		chimpum = this.repository.findOneChimpumById(chimpumId);
		inventorId = chimpum.getItem().getInventor().getId();

		result = chimpum != null && inventorId == request.getPrincipal().getActiveRoleId();
		return result;
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;

		Chimpum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneChimpumById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "moment", "title", "description", "startDate", "endDate", "budget", "link");

		SystemConfiguration sc;
		String systemCurrency;
		Money budget;
		boolean budgetIsInSystemCurrency;
		
		sc = this.repoSys.findSystemConfiguration();
		
		systemCurrency = sc.getSystemCurrency();
	
		budget = entity.getBudget();

		budgetIsInSystemCurrency = systemCurrency.equals(budget.getCurrency());
		model.setAttribute("budgetIsInSystemCurrency", budgetIsInSystemCurrency);
		if (!budgetIsInSystemCurrency) {
			final Money budgetChanged;
			budgetChanged = this.serviceMoney.computeMoneyExchange(budget, systemCurrency).getChange();

			model.setAttribute("budgetChanged", budgetChanged);

		}

		model.setAttribute("itemName", entity.getItem().getName());

	}

}
