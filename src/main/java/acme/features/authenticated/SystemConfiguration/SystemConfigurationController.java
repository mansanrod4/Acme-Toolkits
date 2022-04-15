package acme.features.authenticated.SystemConfiguration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.components.configuration.SystemConfiguration;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class SystemConfigurationController extends AbstractController<Authenticated, SystemConfiguration>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected SystemConfigurationService currencyService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list", this.currencyService);
		}

}
