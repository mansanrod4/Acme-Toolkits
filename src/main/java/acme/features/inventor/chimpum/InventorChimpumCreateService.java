
package acme.features.inventor.chimpum;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.configuration.SystemConfiguration;
import acme.entities.Chimpum;
import acme.entities.toolkits.Item;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import notenoughspam.detector.SpamDetector;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, Chimpum> {

	@Autowired
	protected InventorChimpumRepository						repository;

	@Autowired
	protected AdministratorSystemConfigurationRepository	administratorSystemConfigurationRepository;


	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		boolean result;
		
		Item item;
		final int itemId;
		itemId = request.getModel().getInteger("itemId"); //la del form de item aisha
		item = this.repository.findOneItemById(itemId);
		
		result = item!=null && request.isPrincipal(item.getInventor());

		return result;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		Date moment;

		request.bind(entity, errors, "code", "title", "description", "startDate", "endDate", "budget", "link");
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		final DateFormat formateador = new SimpleDateFormat("yy/MM/dd");
		final String codeStr = formateador.format(new Date());
		entity.setCode(codeStr);

		//selector
		//		entity.setItem(this.repository.findOneItemById(Integer.valueOf(request.getModel().getInteger("itemId").toString())));

	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "moment", "title", "description", "startDate", "endDate", "budget", "link");
		model.setAttribute("itemId", entity.getItem().getId());

		//selector
		//		model.setAttribute("tools", this.repository.findAllToolsByInventor(request.getPrincipal().getActiveRoleId()));

	}

	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {
		assert request != null;

		final Chimpum result;
		final Calendar calendar;
		Date moment;

		moment = new Date();
		calendar = Calendar.getInstance();
		calendar.setTime(moment);
		calendar.add(Calendar.SECOND, -1);
		moment = calendar.getTime();

		final DateFormat formateador = new SimpleDateFormat("yy/MM/dd");
		final String codeStr = formateador.format(new Date());

		result = new Chimpum();
		result.setTitle("");
		result.setDescription("");
		result.setMoment(moment);
		result.setCode(codeStr);

		Item item;
		final int itemId;
		itemId = request.getModel().getInteger("itemId"); //la del form de item aisha
		item = this.repository.findOneItemById(itemId);
		result.setItem(item);

		return result;
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
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
