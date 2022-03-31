package acme.features.any.userAccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyUserAccountRepository extends AbstractRepository{
	
	
	@Query("select ua from UserAccount ua")
	Collection<UserAccount> findAllUserAccounts();
	
//	@Query("select ua from UserAccount ua where ua.hasRole")
//	Collection<UserAccount> findAllUserInventors();
	
}
