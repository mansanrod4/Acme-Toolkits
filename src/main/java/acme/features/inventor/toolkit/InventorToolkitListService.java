package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolkitListService implements AbstractListService<Inventor, Toolkit>{
	
	// Internal State ------------------------------------------------------------------
	
	@Autowired
	protected InventorToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request!=null;
		final int inventorId = request.getPrincipal().getActiveRoleId(); 	
		final Collection<Toolkit> toolkits = this.repository.findAllToolkitsByInventor(inventorId);
		return toolkits.stream().allMatch(e-> e.getInventor().getId()==inventorId);
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request !=null;
		
		final Collection<Toolkit> result;
		final int inventorId = request.getPrincipal().getActiveRoleId();
		result= this.repository.findAllToolkitsByInventor(inventorId);
		
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
