package acme.features.any.toolkits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit>{
	
	// Internal State -------------------------------------------------------------------
	
	@Autowired
	protected AnyToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		final boolean result;
		int id;
		Toolkit toolkit;
		
		id = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(id);
		return toolkit;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request !=null;
		assert entity !=null;
		assert model !=null;
		
		request.unbind(entity, model, "title", "description", "assemblyNotes");
		
	}

}
