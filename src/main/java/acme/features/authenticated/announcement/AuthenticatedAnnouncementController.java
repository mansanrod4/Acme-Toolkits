
package acme.features.authenticated.announcement;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Announcement;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class AuthenticatedAnnouncementController extends AbstractController<Authenticated, Announcement> {

	@Autowired
	protected AuthenticatedAnnouncementListService	listService;

	@Autowired
	protected AuthenticatedAnnouncementShowService	showService;
	
	@Autowired
	protected AuthenticatedAnnouncementCreateService createService;


	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
	}
}
