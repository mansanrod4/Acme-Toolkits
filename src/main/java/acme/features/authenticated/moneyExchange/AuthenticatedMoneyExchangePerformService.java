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

import java.time.LocalDateTime;
import java.time.ZoneId;

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

		request.bind(entity, errors, "targetCurrency");
		final Money source = request.getModel().getAttribute("source", Money.class);
		entity.setSourceCurrency(source.getCurrency());

	}

	@Override
	public void unbind(final Request<MoneyExchange> request, final MoneyExchange entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		
		request.unbind(entity, model, "sourceCurrency", "targetCurrency", "date");
	}

	@Override
	public MoneyExchange instantiate(final Request<MoneyExchange> request) {
		assert request != null;

		MoneyExchange result;

		result = new MoneyExchange();

		return result;
	}

	@Override
	public void validate(final Request<MoneyExchange> request, final MoneyExchange entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("source")) {
			final String currency = entity.getSourceCurrency();
			final boolean currencyIsSuported = this.repository.findAcceptedCurrncies().contains(currency);

			errors.state(request, currencyIsSuported, "source", "authenticated.money-exchange.form.error.source.currency-not-supported");
			errors.state(request, request.getModel().getAttribute("source", Money.class).getAmount()>= 0, "source", "authenticated.money-exchange.form.error.source.negative");
		}

	}

	@Override
	public void perform(final Request<MoneyExchange> request, final MoneyExchange entity, final Errors errors) {
		assert request != null;
		assert entity != null;

		Money source;
		String targetCurrency;
		Money exchange;

		source = request.getModel().getAttribute("source", Money.class);
		targetCurrency = request.getModel().getAttribute("targetCurrency", String.class);
		exchange = this.computeMoneyExchange(source, targetCurrency);
		errors.state(request, exchange != null, "*", "authenticated.money-exchange.form.label.api-error");
		
	}

	// Ancillary methods ------------------------------------------------------

	public Money computeMoneyExchange(final Money moneyToCompute, final String targetCurrency) {
		assert moneyToCompute != null;
		assert !StringHelper.isBlank(targetCurrency);

		MoneyExchange change;
		RestTemplate api;
		ExchangeRate record;
		Money result= new Money();
		Double rate;
		change = this.repository.findMoneyExchangeFromCurrencies(moneyToCompute.getCurrency(), targetCurrency);
		
		if(change==null || change.date.isBefore(LocalDateTime.now().minusDays(1L))) {
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
				
				change=new MoneyExchange();
				change.setDate(record.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
				change.setRate(rate);
				change.setSourceCurrency(moneyToCompute.getCurrency());
				change.setTargetCurrency(targetCurrency);
				
				result.setAmount(change.getRate()*moneyToCompute.getAmount());
				result.setCurrency(targetCurrency);	
				this.repository.save(change);
			} catch (final Throwable oops) {
				result = null;
			}
		}else {
			result.setAmount(change.getRate()*moneyToCompute.getAmount());
			result.setCurrency(targetCurrency);	
		}
		
		return result;
		
	}


}
