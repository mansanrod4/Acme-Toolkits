
package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage> {

	@Autowired
	protected PatronPatronageRepository repository;


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public Patronage instantiate(final Request<Patronage> request) {

		assert request != null;

		final Patronage result;
		final Patron patron;

		patron = this.repository.findOnePatronById(request.getPrincipal().getActiveRoleId());

		result = new Patronage();
		result.setStatus(PatronageStatus.PROPOSED);
		result.setPublished(false);
		result.setCode("");
		result.setPatron(patron);

		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		Date moment;

		request.bind(entity, errors, "code", "legalStuff", "budget", "startDate", "endDate", "info");
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);

		entity.setStatus(PatronageStatus.PROPOSED);
		final int inventorId = request.getModel().getInteger("inventorId");
		entity.setInventor(this.repository.findOneInventorById(inventorId));

	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "status", "legalStuff", "budget", "creationDate", "startDate", "endDate", "info");
		model.setAttribute("inventors", this.repository.findAllInventors());
		model.setAttribute("patron", this.repository.findOnePatronById(request.getPrincipal().getActiveRoleId()));

	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Patronage existing;

			existing = this.repository.findOnePatronageByCode(entity.getCode());
			errors.state(request, existing == null, "code", "patron.patronage.form.error.duplicated");
		}

		if (!errors.hasErrors("startDate")) {
			final Calendar calendar = Calendar.getInstance();
			Date minimunStartDate;
			calendar.setTime(entity.getCreationDate());
			calendar.add(Calendar.MONTH, 1);

			minimunStartDate = calendar.getTime();
			errors.state(request, entity.getStartDate().after(minimunStartDate), "startDate", "patron.patronage.form.error.start-date");

		}

		if (!errors.hasErrors("endDate")) {
			final Calendar calendar = Calendar.getInstance();
			Date minimunEndDate;
			calendar.setTime(entity.getStartDate());
			calendar.add(Calendar.MONTH, 1);

			minimunEndDate = calendar.getTime();
			errors.state(request, entity.getEndDate().after(minimunEndDate), "endDate", "patron.patronage.form.error.end-date");
		}

	}

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
