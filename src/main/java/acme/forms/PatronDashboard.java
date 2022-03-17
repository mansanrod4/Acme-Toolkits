package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Integer numPatronageProposed;
	Integer numPatronageAccepted;
	Integer numPatronageDenied;
	
	Double  averageBudgetProposed;
	Double  desviationBudgetProposed;
	Double  minimunBudgetProposed;
	Double  maximunBudgetProposed;
	
	Double  averageBudgetAccepted;
	Double  desviationBudgetAccepted;
	Double  minimunBudgetAccepted;
	Double  maximunBudgetAccepted;
	
	Double  averageBudgetDenied;
	Double  desviationBudgetDenied;
	Double  minimunBudgetDenied;
	Double  maximunBudgetDenied;

}
