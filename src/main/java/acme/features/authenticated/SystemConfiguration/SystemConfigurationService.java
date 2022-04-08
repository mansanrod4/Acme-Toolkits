package acme.features.authenticated.SystemConfiguration;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class SystemConfigurationService implements AbstractListService<Authenticated, SystemConfiguration>{

	@Autowired
	protected SystemConfigurationRepository repository;

	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<SystemConfiguration> findMany(final Request<SystemConfiguration> request) {
		assert request != null;
		
		Collection<SystemConfiguration> result;
		final int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findConfigurationById(id);

		return result;
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "systemCurrency", "acceptedCurrencies");
	}
	
}
