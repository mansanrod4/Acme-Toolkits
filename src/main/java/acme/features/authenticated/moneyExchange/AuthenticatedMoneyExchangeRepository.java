package acme.features.authenticated.moneyExchange;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMoneyExchangeRepository extends AbstractRepository{
		
	@Query("select sc.acceptedCurrencies from SystemConfiguration sc")
	String findAcceptedCurrncies();
	
}
