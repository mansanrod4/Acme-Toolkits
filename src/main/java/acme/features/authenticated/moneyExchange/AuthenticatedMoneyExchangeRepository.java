package acme.features.authenticated.moneyExchange;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.forms.MoneyExchange;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMoneyExchangeRepository extends AbstractRepository{
		
	@Query("select sc.acceptedCurrencies from SystemConfiguration sc")
	String findAcceptedCurrncies();
	
	@Query("select me from MoneyExchange me where me.sourceCurrency =: source and me.targetCurrency =: targetCurrency")
	MoneyExchange findMoneyExchangeFromCurrencies(String source, String targetCurrency);
	
}
