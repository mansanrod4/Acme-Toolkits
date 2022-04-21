package acme.features.any.toolkits;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolkitListService implements AbstractListService<Any, Toolkit>{
	
	// Internal State ------------------------------------------------------------------
	
	@Autowired
	protected AnyToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request !=null;
		
		final Collection<Toolkit> result;
		
		result= this.repository.findAllToolkits();
		
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request !=null;
		assert entity !=null;
		assert model !=null;
		
		final Double price=this.repository.getToolkitPriceById(entity.getId());
		final Money money = new Money();
		money.setAmount(price);
		money.setCurrency("EUR");
		
		request.unbind(entity, model, "title", "description", "price");
		
		model.setAttribute("price", money);
	}

}
