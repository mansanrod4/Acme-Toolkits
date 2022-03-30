package acme.features.authenticated.userAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.framework.features.authenticated.userAccount.AuthenticatedUserAccountController;

@Controller
@RequestMapping("authenticated/user-account/")
public class AuthenticatedUserAccountController2 extends AuthenticatedUserAccountController{

	@Autowired
	protected AuthenticatedUserAccountListInventorService listInventorService;
	
	@Autowired
	protected AuthenticatedUserAccountListPatronService listPatronService;
	
	@Override
	protected void initialise() {
		super.addCommand("list-inventor", "list", this.listInventorService);
		super.addCommand("list-patron", "list", this.listPatronService);
	}
	
}
