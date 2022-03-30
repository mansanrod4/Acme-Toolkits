package acme.features.authenticated.userAccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.features.authenticated.userAccount.AuthenticatedUserAccountRepository;

@Repository
public interface AuthenticatedUserAccountRepository2 extends AuthenticatedUserAccountRepository{
	
	
	@Query("select ua from UserAccount ua")
	Collection<UserAccount> findAllUserAccounts();
	
//	@Query("select ua from UserAccount ua where ua.hasRole")
//	Collection<UserAccount> findAllUserInventors();
	
}
