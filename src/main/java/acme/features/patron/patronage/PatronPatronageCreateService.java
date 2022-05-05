
package acme.features.patron.patronage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage> {

	@Autowired
	protected PatronPatronageRepository repository;


	@Override
	public boolean authorise(final Request<Patronage> request) {

		assert request != null;

		boolean result;
		int inventorId;
		Inventor inventor;

		inventorId = request.getModel().getInteger("inventorId");
		inventor = this.repository.findOneInventorById(inventorId);
		result = inventor != null;

		return result;
	}
	

	@Override
	public Patronage instantiate(final Request<Patronage> request) {

		assert request != null;
		
		final Patronage result;
		Patron patron;
		final Inventor inventor;
		final PatronageStatus status;
		final Money budget;
		final Date creationDate, startDate, endDate;
		
		patron = this.repository.findOnePatronById(request.getPrincipal().getActiveRoleId());
		inventor = this.repository.findOneInventorById(request.getModel().getInteger("inventorId"));
		
		result = new Patronage();
		result.setStatus(PatronageStatus.PROPOSED);
		
		result.setPatron(patron);
		result.setInventor(inventor);
			
		return null;
	}


	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		// TODO Auto-generated method stub

	}

}
