
package acme.features.authenticated.systemConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedSystemConfigurationShowService implements AbstractShowService<Authenticated, SystemConfiguration> {

	@Autowired
	protected AuthenticatedSystemConfigurationRepository repository;


	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;
		return true;
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;
		SystemConfiguration result;

		result = this.repository.findSystemConfiguration();

		return result;
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "systemCurrency", "acceptedCurrencies");
		final MoneyExchange mE = new MoneyExchange();
		final Money defaultMoney = new Money();
		defaultMoney.setAmount(1.0);
		defaultMoney.setCurrency("EUR");
		
//		final String[] acceptedCurrencies = this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
		
		model.setAttribute("USDexchange", mE.computeMoneyExchange(defaultMoney, "USD").target);
		model.setAttribute("GBPexchange", mE.computeMoneyExchange(defaultMoney, "GBP").target);

	}

}
