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
		final EnumMap<PatronageStatus, Integer> numPatronagesByStatus;
//		numPatronagesByStatus = new EnumMap<>(PatronageStatus.class);
		final Map<Pair<PatronageStatus, String>, Double> averageBudgetsByStatus;
		final Map<Pair<PatronageStatus, String>, Double> deviationBudgetsByStatus;
		Map<Pair<PatronageStatus, String>, Double> minBudgetByStatus;
		Map<Pair<PatronageStatus, String>, Double> maxBudgetByStatus;

//		final Integer numPatronagesByStatus = this.repository.numPatronagesByStatus(PatronageStatus.PROPOSED);
//		final Integer numPatronagesByStatus = this.repository.numPatronagesByStatus(PatronageStatus.ACCEPTED);
//		final Integer numPatronagesByStatus = this.repository.numPatronagesByStatus(PatronageStatus.DENIED);
//		numOfPatronagesByStatus.put(PatronageStatus.PROPOSED, numPatronagesByStatus);
//		numOfPatronagesByStatus.put(PatronageStatus.ACCEPTED, numPatronagesByStatus);
//		numOfPatronagesByStatus.put(PatronageStatus.DENIED, numPatronagesByStatus);
//		
		averageBudgetsByStatus = new HashMap<>();	
		this.repository.averageBudgetsByStatus(PatronageStatus.PROPOSED).stream()
		.forEach(x->
		averageBudgetsByStatus.put(
				Pair.of(PatronageStatus.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.averageBudgetsByStatus(PatronageStatus.ACCEPTED).stream()
		.forEach(x->
		averageBudgetsByStatus.put(
				Pair.of(PatronageStatus.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.averageBudgetsByStatus(PatronageStatus.DENIED).stream()
		.forEach(x->
		averageBudgetsByStatus.put(
				Pair.of(PatronageStatus.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		
		deviationBudgetsByStatus = new HashMap<Pair<PatronageStatus, String>, Double>();	
		this.repository.deviationBudgetsByStatus(PatronageStatus.PROPOSED).stream()
		.forEach(x->
		deviationBudgetsByStatus.put(
				Pair.of(PatronageStatus.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.deviationBudgetsByStatus(PatronageStatus.ACCEPTED).stream()
		.forEach(x->
		deviationBudgetsByStatus.put(
				Pair.of(PatronageStatus.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.deviationBudgetsByStatus(PatronageStatus.DENIED).stream()
		.forEach(x->
		deviationBudgetsByStatus.put(
				Pair.of(PatronageStatus.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		
		minBudgetByStatus = new HashMap<Pair<PatronageStatus, String>, Double>();	
		this.repository.minBudgetByStatus(PatronageStatus.PROPOSED).stream()
		.forEach(x->
		minBudgetByStatus.put(
				Pair.of(PatronageStatus.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.minBudgetByStatus(PatronageStatus.ACCEPTED).stream()
		.forEach(x->
		minBudgetByStatus.put(
				Pair.of(PatronageStatus.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.minBudgetByStatus(PatronageStatus.DENIED).stream()
		.forEach(x->
		minBudgetByStatus.put(
				Pair.of(PatronageStatus.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		
		maxBudgetByStatus = new HashMap<Pair<PatronageStatus, String>, Double>();	
		this.repository.maxBudgetByStatus(PatronageStatus.PROPOSED).stream()
		.forEach(x->
		maxBudgetByStatus.put(
				Pair.of(PatronageStatus.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.maxBudgetByStatus(PatronageStatus.ACCEPTED).stream()
		.forEach(x->
		maxBudgetByStatus.put(
				Pair.of(PatronageStatus.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.maxBudgetByStatus(PatronageStatus.DENIED).stream()
		.forEach(x->
		maxBudgetByStatus.put(
				Pair.of(PatronageStatus.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);

		result = new PatronDashboard();
//		result.setNumPatronagesByStatus(numPatronagesByStatus);
		result.setAverageBudgetsByStatus(averageBudgetsByStatus);
		result.setDeviationBudgetsByStatus(deviationBudgetsByStatus);
		result.setMinBudgetByStatus(minBudgetByStatus);
		result.setMaxBudgetByStatus(maxBudgetByStatus);
		
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
