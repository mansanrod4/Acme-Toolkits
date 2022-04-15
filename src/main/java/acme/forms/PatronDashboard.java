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
	
//	Money   averageBudgetProposed;
//	Money   desviationBudgetProposed;
//	Money   minimunBudgetProposed;
//	Money   maximunBudgetProposed;
//	
//	Money   averageBudgetAccepted;
//	Money   desviationBudgetAccepted;
//	Money   minimunBudgetAccepted;
//	Money   maximunBudgetAccepted;
//	
//	Money   averageBudgetDenied;
//	Money   desviationBudgetDenied;
//	Money   minimunBudgetDenied;
//	Money   maximunBudgetDenied;

}
