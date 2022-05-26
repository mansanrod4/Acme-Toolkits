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

package acme.features.authenticated.moneyExchange;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import acme.components.ExchangeRate;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.helpers.StringHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractPerformService;

@Service
public class AuthenticatedMoneyExchangePerformService implements AbstractPerformService<Authenticated, MoneyExchange> {

	@Autowired
	protected AuthenticatedMoneyExchangeRepository repository;

	// AbstractPerformService<Authenticated, ExchangeRecord> interface ---------


	@Override
	public boolean authorise(final Request<MoneyExchange> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<MoneyExchange> request, final MoneyExchange entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "source", "targetCurrency", "date", "change");

	}

	@Override
	public void unbind(final Request<MoneyExchange> request, final MoneyExchange entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "source", "targetCurrency", "date", "change");

	}

	@Override
	public MoneyExchange instantiate(final Request<MoneyExchange> request) {
		assert request != null;

		MoneyExchange result;

		result = new MoneyExchange();
		result.setRate(1.0);
		final Money source = new Money();
		source.setAmount(1.);
		source.setCurrency(this.repository.findSystemCurrency());
		result.setSource(source);
		result.setTargetCurrency("USD");

		return result;
	}

	@Override
	public void validate(final Request<MoneyExchange> request, final MoneyExchange entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final String acceptedCurrencies = this.repository.findAcceptedCurrencies();

		if (!errors.hasErrors("source")) {
			final String currency = entity.getSource().getCurrency();

			final boolean currencyIsSuportedS = acceptedCurrencies.contains(currency);
			errors.state(request, currencyIsSuportedS, "source", "authenticated.money-exchange.form.error.source.currency-not-supported");
			errors.state(request, entity.getSource().getAmount() >= 0, "source", "authenticated.money-exchange.form.error.source.negative");
		}
		
		if (!errors.hasErrors("targetCurrency")) {
			
			final boolean currencyIsSuportedT = acceptedCurrencies.contains(entity.getTargetCurrency());
			errors.state(request, currencyIsSuportedT, "targetCurrency", "authenticated.money-exchange.form.error.source.currency-not-supported");
		}

	}

	@Override
	public void perform(final Request<MoneyExchange> request, final MoneyExchange entity, final Errors errors) {
		assert request != null;
		assert entity != null;

		Money source;
		String targetCurrency;
		MoneyExchange exchange;

		source = request.getModel().getAttribute("source", Money.class);
		targetCurrency = request.getModel().getAttribute("targetCurrency", String.class);
		exchange = this.computeMoneyExchange(source, targetCurrency);

		errors.state(request, exchange != null, "*", "authenticated.money-exchange.form.label.api-error");

		if (exchange == null) {
			entity.setDate(null);
			entity.setRate(null);
		} else {
			entity.setRate(exchange.getRate());
			entity.setDate(exchange.getDate());
			final Money target = new Money();
			target.setAmount(entity.getSource().getAmount() * entity.getRate());
			target.setCurrency(entity.getTargetCurrency());
			entity.setChange(target);

		}

	}

	// Ancillary methods ------------------------------------------------------

	public MoneyExchange computeMoneyExchange(final Money moneyToCompute, final String targetCurrency) {
		assert moneyToCompute != null;
		assert !StringHelper.isBlank(targetCurrency);

		MoneyExchange change;
		RestTemplate api;
		ExchangeRate record;
		Double rate;
		change = this.repository.findMoneyExchangeFromCurrencies(moneyToCompute.getCurrency(), targetCurrency);

		final Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		final Date yesterday = c.getTime();

		if (change == null || change.getDate().before(yesterday)) {
			try {

				api = new RestTemplate();

				record = api.getForObject( //
					"https://api.exchangerate.host/latest?base={0}&symbols={1}", //
					ExchangeRate.class, //
					moneyToCompute.getCurrency(), //
					targetCurrency //
				);

				assert record != null;
				rate = record.getRates().get(targetCurrency);

				change = new MoneyExchange();
				change.setSource(moneyToCompute);
				change.setTargetCurrency(targetCurrency);
				change.setRate(rate);
				change.setDate(record.getDate());

				this.repository.save(change);
			} catch (final Throwable oops) {
				change = null;
			}
		}

		return change;

	}

}
