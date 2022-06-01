
package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorChimpumDeleteService implements AbstractDeleteService<Inventor, Chimpum> {

	@Autowired
	protected InventorChimpumRepository						repository;

	@Autowired
	protected AdministratorSystemConfigurationRepository	administratorSystemConfigurationRepository;


	@Override
	public boolean authorise(final Request<Chimpum> request) {
		///mismo que show
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
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "startDate", "endDate", "budget", "link");

	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		model.setAttribute("readonly", false);
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "moment", "title", "description", "startDate", "endDate", "budget", "link");
	}
	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;

		final Integer id = request.getModel().getInteger("id");
		return this.repository.findOneChimpumById(id);
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);

	}

}
