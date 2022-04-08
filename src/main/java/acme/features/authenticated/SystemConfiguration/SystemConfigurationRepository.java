package acme.features.authenticated.SystemConfiguration;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SystemConfigurationRepository extends AbstractRepository{
	
	@Query("select sc from SystemConfiguration sc where sc.id = :id")
	Collection<SystemConfiguration> findConfigurationById(int id);

}
