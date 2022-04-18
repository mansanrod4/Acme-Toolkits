/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.patron.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronDashboardRepository repository;

	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;

		final PatronDashboard result;
		Integer numPatronageProposed;
		Integer numPatronageAccepted;
		Integer numPatronageDenied;

		numPatronageProposed = this.repository.numPatronageProposed();
		numPatronageAccepted = this.repository.numPatronageAccepted();
		numPatronageDenied = this.repository.numPatronageDenied();

		result = new PatronDashboard();
		result.setNumPatronageAccepted(numPatronageAccepted);
		result.setNumPatronageProposed(numPatronageProposed);
		result.setNumPatronageDenied(numPatronageDenied);
//		result.setAvegageNumberOfApplicationsPerEmployer(averageNumberOfApplicationsPerEmployer);
//		result.setAverageNumberOfApplicationsPerWorker(averageNumberOfApplicationsPerWorker);
//		result.setAverageNumberOfJobsPerEmployer(averageNumberOfJobsPerEmployer);
//		result.setRatioOfPendingApplications(ratioOfPendingApplications);
//		result.setRatioOfAcceptedApplications(ratioOfAcceptedApplications);
//		result.setRatioOfRejectedApplications(ratioOfRejectedApplications);

		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"numPatronageProposed", "numPatronageAccepted", // 
			"numPatronageDenied");
		model.setAttribute("maximo", this.numMaxPatronage(this.repository.numPatronageProposed(), 
			this.repository.numPatronageAccepted(), this.repository.numPatronageDenied()));
	}
	
	public Integer numMaxPatronage(final Integer numPatronageProposed, final Integer numPatronageAccepted,
		final Integer numPatronageDenied) {
		Integer max = 0;
		Integer res = 2;
		
		if(max<numPatronageAccepted) {
			max = numPatronageAccepted;
		} 
		
		if(max<numPatronageProposed) {
			max = numPatronageProposed;
		}
		
		if(max<numPatronageDenied) {
			max = numPatronageDenied;
		}
		res = res + max;
		return res;
		
	}

}
