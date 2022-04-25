
package acme.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.client.RestTemplate;

import acme.components.ExchangeRate;
import acme.framework.datatypes.Money;
import acme.framework.helpers.StringHelper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoneyExchange {

	// Query attributes -------------------------------------------------------

	@NotNull
	@Valid
	public Money	source;

	@NotBlank
	public String	targetCurrency;

	// Response attributes ----------------------------------------------------

	@Valid
	public Money	target;

	public Date		date;


	public MoneyExchange computeMoneyExchange(final Money source, final String targetCurrency) {
		assert source != null;
		assert !StringHelper.isBlank(targetCurrency);

		MoneyExchange result;
		RestTemplate api;
		ExchangeRate record;
		String sourceCurrency;
		Double sourceAmount, targetAmount, rate;
		Money targetMoney;

		try {
			api = new RestTemplate();

			sourceCurrency = source.getCurrency();
			sourceAmount = source.getAmount();

			record = api.getForObject( //
				"https://api.exchangerate.host/latest?base={0}&symbols={1}", //
				ExchangeRate.class, //
				sourceCurrency, //
				targetCurrency //
			);

			assert record != null;
			rate = record.getRates().get(targetCurrency);
			targetAmount = rate * sourceAmount;

			targetMoney = new Money();
			targetMoney.setAmount(targetAmount);
			targetMoney.setCurrency(targetCurrency);

			result = new MoneyExchange();
			result.setSource(source);
			result.setTargetCurrency(targetCurrency);
			result.setDate(record.getDate());
			result.setTarget(targetMoney);
		} catch (final Throwable oops) {
			result = null;
		}

		return result;
	}

	public List<Money> convertMoney(final List<Money> ls, final String targetCurrency) {
		final List<Money> resLs = new ArrayList<>();
		for (Money price : ls) {
			if (price.getAmount() == null) {
				price.setAmount(0.);
				price.setCurrency(targetCurrency);
			} else if (!price.getCurrency().equals(targetCurrency)) {
				price = (this.computeMoneyExchange(price, targetCurrency).target);
			}
			resLs.add(price);
		}
		return resLs;
	}

	//	public StatData getPricesData(final List<Money> ls, final String targetCurrency){
	//		final StatData res= new StatData();
	//		final List<Money> newLs=this.convertMoney(ls, targetCurrency);
	//		final Double sum=newLs.stream().mapToDouble(x->x.getAmount()).sum();
	//		final Double avg=newLs.stream().mapToDouble(x->x.getAmount()).average().getAsDouble();
	//		//final Double desv=newLs.stream().mapToDouble(x->x.getAmount());
	//		final Double max=newLs.stream().mapToDouble(x->x.getAmount()).max().getAsDouble();
	//		final Double min=newLs.stream().mapToDouble(x->x.getAmount()).min().getAsDouble();
	//		final Money valor= new Money();
	//		valor.setCurrency(targetCurrency);
	//		valor.setAmount(avg); res.setAverage(valor);
	//		valor.setAmount(sum); res.setSum(valor);
	//	//	valor.setAmount(desv); res.setDesviation(valor);
	//		valor.setAmount(min); res.setMinimum(valor);
	//		valor.setAmount(max); res.setMaximum(valor);
	//		
	//	}

}
