package acme.features.inventor.chimpum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Chimpum;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorChimpumController extends AbstractController<Inventor, Chimpum> {
	
	@Autowired
	protected InventorChimpumListService listService;
	
	@Autowired
	protected InventorChimpumShowService showService;
	
	@Autowired
	protected InventorChimpumCreateService createService;
	
	@Autowired
	protected InventorChimpumUpdateService updateService;

	@Autowired
	protected InventorChimpumDeleteService deleteService;
	

	
	@PostConstruct
	protected void initialse() {
//		super.addCommand("change-status","update", this.inventorPatronageAcceptService);
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
	}
}
