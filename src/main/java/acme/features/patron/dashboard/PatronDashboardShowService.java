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

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronages.PatronageStatus;
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
		boolean result;

		result = request.getPrincipal().hasRole(Patron.class);

		return result;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;

		final PatronDashboard result;
		EnumMap<PatronageStatus, Integer> numberOfPatronagesByStatus;
		numberOfPatronagesByStatus = new EnumMap<>(PatronageStatus.class);
		Map<Pair<PatronageStatus, String>, Double> averageNumberOfBudgetsByCurrencyAndStatus;
		Map<Pair<PatronageStatus, String>, Double> deviationOfBudgetsByCurrencyAndStatus;
		Map<Pair<PatronageStatus, String>, Double> minBudgetByCurrencyAndStatus;
		Map<Pair<PatronageStatus, String>, Double> maxBudgetByCurrencyAndStatus;

		final Integer numberOfProposedPatronages = this.repository.numberOfPatronagesByStatus(PatronageStatus.PROPOSED);
		final Integer numberOfAcceptedPatronages = this.repository.numberOfPatronagesByStatus(PatronageStatus.ACCEPTED);
		final Integer numberOfDeniedPatronages = this.repository.numberOfPatronagesByStatus(PatronageStatus.DENIED);
		numberOfPatronagesByStatus.put(PatronageStatus.PROPOSED, numberOfProposedPatronages);
		numberOfPatronagesByStatus.put(PatronageStatus.ACCEPTED, numberOfAcceptedPatronages);
		numberOfPatronagesByStatus.put(PatronageStatus.DENIED, numberOfDeniedPatronages);
		
		averageNumberOfBudgetsByCurrencyAndStatus = new HashMap<>();	
		this.repository.averageNumberOfBudgetsByCurrencyAndStatus(PatronageStatus.PROPOSED).stream()
		.forEach(x->
			averageNumberOfBudgetsByCurrencyAndStatus.put(
				Pair.of(PatronageStatus.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.averageNumberOfBudgetsByCurrencyAndStatus(PatronageStatus.ACCEPTED).stream()
		.forEach(x->
			averageNumberOfBudgetsByCurrencyAndStatus.put(
				Pair.of(PatronageStatus.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.averageNumberOfBudgetsByCurrencyAndStatus(PatronageStatus.DENIED).stream()
		.forEach(x->
			averageNumberOfBudgetsByCurrencyAndStatus.put(
				Pair.of(PatronageStatus.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		
		deviationOfBudgetsByCurrencyAndStatus = new HashMap<Pair<PatronageStatus, String>, Double>();	
		this.repository.deviationOfBudgetsByCurrencyAndStatus(PatronageStatus.PROPOSED).stream()
		.forEach(x->
		deviationOfBudgetsByCurrencyAndStatus.put(
				Pair.of(PatronageStatus.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.deviationOfBudgetsByCurrencyAndStatus(PatronageStatus.ACCEPTED).stream()
		.forEach(x->
		deviationOfBudgetsByCurrencyAndStatus.put(
				Pair.of(PatronageStatus.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.deviationOfBudgetsByCurrencyAndStatus(PatronageStatus.DENIED).stream()
		.forEach(x->
		deviationOfBudgetsByCurrencyAndStatus.put(
				Pair.of(PatronageStatus.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		
		minBudgetByCurrencyAndStatus = new HashMap<Pair<PatronageStatus, String>, Double>();	
		this.repository.minBudgetByCurrencyAndStatus(PatronageStatus.PROPOSED).stream()
		.forEach(x->
		minBudgetByCurrencyAndStatus.put(
				Pair.of(PatronageStatus.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.minBudgetByCurrencyAndStatus(PatronageStatus.ACCEPTED).stream()
		.forEach(x->
		minBudgetByCurrencyAndStatus.put(
				Pair.of(PatronageStatus.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.minBudgetByCurrencyAndStatus(PatronageStatus.DENIED).stream()
		.forEach(x->
		minBudgetByCurrencyAndStatus.put(
				Pair.of(PatronageStatus.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		
		maxBudgetByCurrencyAndStatus = new HashMap<Pair<PatronageStatus, String>, Double>();	
		this.repository.maxBudgetByCurrencyAndStatus(PatronageStatus.PROPOSED).stream()
		.forEach(x->
		maxBudgetByCurrencyAndStatus.put(
				Pair.of(PatronageStatus.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.maxBudgetByCurrencyAndStatus(PatronageStatus.ACCEPTED).stream()
		.forEach(x->
		maxBudgetByCurrencyAndStatus.put(
				Pair.of(PatronageStatus.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.maxBudgetByCurrencyAndStatus(PatronageStatus.DENIED).stream()
		.forEach(x->
		maxBudgetByCurrencyAndStatus.put(
				Pair.of(PatronageStatus.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);

		result = new PatronDashboard();
		result.setNumberOfPatronagesByStatus(numberOfPatronagesByStatus);
		result.setAverageNumberOfBudgetsByCurrencyAndStatus(averageNumberOfBudgetsByCurrencyAndStatus);
		result.setDeviationOfBudgetsByCurrencyAndStatus(deviationOfBudgetsByCurrencyAndStatus);
		result.setMinBudgetByCurrencyAndStatus(minBudgetByCurrencyAndStatus);
		result.setMaxBudgetByCurrencyAndStatus(maxBudgetByCurrencyAndStatus);
		
		return result;
		
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"numberOfPatronagesByStatus", "averageNumberOfBudgetsByCurrencyAndStatus", // 
			"deviationOfBudgetsByCurrencyAndStatus", "minBudgetByCurrencyAndStatus", //
			"maxBudgetByCurrencyAndStatus");
	
	}
	//

	
}
