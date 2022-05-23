
package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage> {

	@Autowired
	protected InventorPatronageRepository repository;


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		int patronageId;
		Patronage patronage;

		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(patronageId);
		result = patronage != null && patronage.getInventor().getId() == request.getPrincipal().getActiveRoleId();
		result = result && patronage.isPublished();
		
		return result;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "code", "legalStuff", "budget", "creationDate", "startDate", "endDate", "info", "status");
		model.setAttribute("inventorId", entity.getInventor().getId());
		model.setAttribute("patronId", entity.getPatron().getId());
		model.setAttribute("patronCompany", entity.getPatron().getCompany());
		model.setAttribute("patronStatement", entity.getPatron().getStatement());
		model.setAttribute("patronFullName", entity.getPatron().getIdentity().getFullName());
		model.setAttribute("patronEmail", entity.getPatron().getIdentity().getEmail());
		model.setAttribute("patronInfo", entity.getPatron().getInfo());

	}

}
