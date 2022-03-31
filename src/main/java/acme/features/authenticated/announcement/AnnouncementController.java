package acme.features.authenticated.announcement;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Announcement;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

public class AnnouncementController extends AbstractController<Authenticated, Announcement>{

	@Autowired
	protected AnnouncementServiceList	service;


	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.service);
	}
}
