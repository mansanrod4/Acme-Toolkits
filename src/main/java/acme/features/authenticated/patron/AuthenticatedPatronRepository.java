package acme.features.authenticated.patron;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedPatronRepository extends AbstractRepository{
	
	@Query("select count(p) from Patronage p where p.status = 'PROPOSED'")
	Integer numPatronageProposed();
	@Query("select count(p) from Patronage p where p.status = 'ACCEPTED'")
	Integer numPatronageAccepted();
	@Query("select count(p) from Patronage p where p.status = 'DENIED'")
	Integer numPatronageDenied();
	
	@Query()
	Double  averageBudgetProposed();
	Double  desviationBudgetProposed();
	Double  minimunBudgetProposed();
	Double  maximunBudgetProposed();
	

}
