package acme.features.authenticated.systemConfiguration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.configuration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedSystemConfigurationRepository extends AbstractRepository{

	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();

}