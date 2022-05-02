package acme.features.authenticated.announcement;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAnnouncementCreateService implements AbstractCreateService<Authenticated, Announcement>{

	@Autowired
	protected AuthenticatedAnnouncementRepository repository;
	
	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;

		boolean result;

		result = request.getPrincipal().hasRole(Administrator.class);

		return result;
	}

	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		request.bind(entity, errors, "title", "body", "critical", "link");
		entity.setMoment(moment);
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,  "title", "body", "critical", "link");
		
	}

	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;

		Announcement result;		
			
		result = new Announcement();
		result.setTitle("");
		result.setBody("");
		result.setCritical(false);
		result.setLink("");

		return result;
	}
	
	public Date convertToDateViaSqlTimestamp() {
		final LocalDateTime date = LocalDateTime.now();
	    return java.sql.Timestamp.valueOf(date);
	}

	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
