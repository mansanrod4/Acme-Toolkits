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

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Tuple;

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
		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;

		final PatronDashboard result;
		final Map<PatronageStatus, Integer> numPatronagesByStatus = 
			new EnumMap<PatronageStatus, Integer>(PatronageStatus.class);
		final Map<Pair<PatronageStatus, String>, Double> averageBudgetsByStatus = new HashMap<>();
		final Map<Pair<PatronageStatus, String>, Double> deviationBudgetsByStatus = new HashMap<>();
		final Map<Pair<PatronageStatus, String>, Double> minBudgetByStatus = new HashMap<>();
		final Map<Pair<PatronageStatus, String>, Double> maxBudgetByStatus = new HashMap<>();

		
		for(final PatronageStatus ps: PatronageStatus.values()) {
			//Num patronage 
			final Integer numPatronages = this.repository.numPatronagesByStatus(ps);
			numPatronagesByStatus.put(ps, numPatronages);
			//Average
			final Collection<Tuple> average = this.repository.averageBudgetsByStatus(ps);
			average.stream().forEach(x-> averageBudgetsByStatus.put(
				Pair.of(ps, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
			//Desviation
			final Collection<Tuple> desviation = this.repository.deviationBudgetsByStatus(ps);
			desviation.stream().forEach(x-> deviationBudgetsByStatus.put(
				Pair.of(ps, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
			//Minimum
			final Collection<Tuple> minimum = this.repository.minBudgetByStatus(ps);
			minimum.stream().forEach(x-> minBudgetByStatus.put(
				Pair.of(ps, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
			//Maximum
			final Collection<Tuple> maximum = this.repository.maxBudgetByStatus(ps);
			maximum.stream().forEach(x-> maxBudgetByStatus.put(
				Pair.of(ps, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString())));
			
		}
	
		//Create Dashboard
		result = new PatronDashboard();
		result.setNumPatronagesByStatus(numPatronagesByStatus);
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

		request.unbind(entity, model, "numPatronagesByStatus", "averageBudgetsByStatus", "deviationBudgetsByStatus", 
			"minBudgetByStatus", "maxBudgetByStatus");
	
	}
	

	
}
