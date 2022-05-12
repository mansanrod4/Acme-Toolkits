
package acme.features.inventor.patronageReport;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

	@Autowired
	public InventorPatronageReportRepository repository;


	@Override
	public boolean authorise(final Request<PatronageReport> request) {

		assert request != null;

		boolean result;
		int patronageReportId;
		Patronage p;

		patronageReportId = request.getModel().getInteger("id");
		p = this.repository.findOnePatronageById(patronageReportId);
		result = p != null && request.isPrincipal(p.getInventor());

		return result;

	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "memorandum", "info", "sequenceNumber");

	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "memorandum", "info", "sequenceNumber");
		model.setAttribute("patronageId", entity.getPatronage().getId());

		model.setAttribute("confirmation", false);
		//		model.setAttribute("readonly", false);	
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {

		assert request != null;
		final PatronageReport result;
		Patronage patronage;
		String reference;
		Date moment;
		final Calendar calendar;

		//		inventor = this.repository.findOneInventorById(request.getPrincipal().getActiveRoleId());
		int patronageReportId;
		patronageReportId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(patronageReportId);
		reference = UUID.randomUUID().toString();

		moment = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(moment);
		calendar.add(Calendar.SECOND, -1);
		moment = calendar.getTime();

		result = new PatronageReport();
		result.setInfo("");
		result.setMemorandum("");
		result.setPatronage(patronage);
		result.setSequenceNumber(reference);
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		boolean confirmation;
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		//		Date moment;
		//		Calendar calendar;
		//
		//		moment = new Date();
		//		calendar = Calendar.getInstance();
		//		calendar.setTime(moment);
		//		calendar.add(Calendar.SECOND, -1);
		//		moment = calendar.getTime();
		//
		//		entity.setMoment(moment);

		this.repository.save(entity);

	}

}
