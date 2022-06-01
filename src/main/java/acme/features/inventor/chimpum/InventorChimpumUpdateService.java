package acme.features.inventor.chimpum;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.entities.Chimpum;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import notenoughspam.detector.SpamDetector;

@Service
public class InventorChimpumUpdateService implements AbstractUpdateService<Inventor, Chimpum>{

	@Autowired
	protected InventorChimpumRepository repository;
	
	@Autowired
	protected AdministratorSystemConfigurationRepository administratorSystemConfigurationRepository;
	
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {

		//mismo que show
		boolean result;
		int chimpumId;
		Chimpum chimpum;
		int inventorId;
		

		chimpumId = request.getModel().getInteger("id");
		chimpum = this.repository.findOneChimpumById(chimpumId);
		inventorId = chimpum.getItem().getInventor().getId();
		
		result = chimpum!= null && inventorId == request.getPrincipal().getActiveRoleId();
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

		if (!errors.hasErrors("startDate")) {
			final Calendar calendar = Calendar.getInstance();
			Date minimunStartDate;
			calendar.setTime(entity.getMoment());
			calendar.add(Calendar.MONTH, 1);

			minimunStartDate = calendar.getTime();
			errors.state(request, entity.getStartDate().after(minimunStartDate), "startDate", "patron.patronage.form.error.start-date");

		}

		if (!errors.hasErrors("endDate")) {
			final Calendar calendar = Calendar.getInstance();
			Date minimunEndDate;
			calendar.setTime(entity.getStartDate());
			calendar.add(Calendar.MINUTE, 10080);

			minimunEndDate = calendar.getTime();
			errors.state(request, entity.getEndDate().after(minimunEndDate), "endDate", "inventor.chimpum.form.error.end-date");
		}

		if (!errors.hasErrors("budget")) {
			final Money b = entity.getBudget();

			errors.state(request, b.getAmount() > 0.0, "budget", "patron.patronage.form.error.budget-negative");

			final String c = b.getCurrency();
			errors.state(request, this.administratorSystemConfigurationRepository.findAcceptedCurrncies().contains(c), "budget", "inventor.chimpum.form.error.budget-acceptedcurrencies");
		}

		final SystemConfiguration sc = this.administratorSystemConfigurationRepository.findSystemConfiguration();
		final SpamDetector spamDetector = new SpamDetector(sc.getStrongSpamThreshold(), sc.getWeakSpamThreshold(), sc.getStrongSpamTerms().split(","), sc.getWeakSpamTerms().split(","));
		if (!errors.hasErrors("description")) {
			final boolean noSpam = spamDetector.stringHasNoSpam(entity.getCode());
			errors.state(request, noSpam, "code", "spam.detector.error.message");
		}
		if (!errors.hasErrors("title")) {
			final boolean noSpam = spamDetector.stringHasNoSpam(entity.getCode());
			errors.state(request, noSpam, "code", "spam.detector.error.message");
		}
	}

	@Override
	public void update(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
