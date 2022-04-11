package acme.features.authenticated.money;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface MoneyRepository extends AbstractRepository{

	@Query("select sc from SystemConfiguration sc")
	Collection<SystemConfiguration> findSystemConfiguration();

}