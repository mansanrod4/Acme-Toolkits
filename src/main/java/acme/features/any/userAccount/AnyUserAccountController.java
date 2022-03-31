package acme.features.any.userAccount;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;

@Controller
public class AnyUserAccountController extends AbstractController<Any, UserAccount>{

	@Autowired
	protected AnyUserAccountListInventorService listInventorService;
	
	@Autowired
	protected AnyUserAccountListPatronService listPatronService;
	
	@Autowired
	protected AnyUserAccountShowService showService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-inventor", "list", this.listInventorService);
		super.addCommand("list-patron", "list", this.listPatronService);
		super.addCommand("show", this.showService);
	}
	
}
