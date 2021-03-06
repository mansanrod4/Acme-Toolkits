package acme.features.administrator.systemConfiguration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSystemConfigurationUpdateService implements AbstractUpdateService<Administrator, SystemConfiguration>{

	@Autowired
	protected AdministratorSystemConfigurationRepository repository;
	
	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "systemCurrency", "acceptedCurrencies", "strongSpamTerms", "strongSpamThreshold", "weakSpamTerms", "weakSpamThreshold");
	}
	

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final String[] acepCurrencies = this.repository.findAcceptedCurrncies().split(",");
		final List<String> accepted = new ArrayList<String>();
		
		for(int i = 0; i<acepCurrencies.length; i++) {
			final String money = acepCurrencies[i];
			accepted.add(money);
		}
		request.unbind(entity, model, "systemCurrency", "acceptedCurrencies", "strongSpamTerms", "strongSpamThreshold", "weakSpamTerms", "weakSpamThreshold");
		model.setAttribute("accepted", accepted);
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;
		SystemConfiguration result;

		result = this.repository.findSystemConfiguration();

		return result;
	}

	@Override
	public void validate(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(final Request<SystemConfiguration> request, final SystemConfiguration entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
