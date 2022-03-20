package acme.forms;

import java.io.Serializable;
import java.util.Map;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDashboard implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	
	Integer numComponents;
	Integer numTools;
	Integer numPatronageRequested;
	Integer numPatronageAccepted;
	Integer numPatronageDenied;
	
	Map<String, Money> avgRetailerCompPrice;
	Map<String, Money> deviationRetailerCompPrice;
	Map<String, Money> minRetailerCompPrice;
	Map<String, Money> maxRetailerCompPrice;
	
	Map<String, Money> avgRetailerToolPrice;
	Map<String, Money> deviationRetailerToolPrice;
	Map<String, Money> minRetailerToolPrice;
	Map<String, Money> maxRetailerToolPrice;
	
	Money avgPatronagRequestedBudget;
	Money deviationPatronagRequestedBudget;
	Money minPatronagRequestedBudget;
	Money maxPatronagRequestedBudget;
	
	Money avgPatronageAcceptedBudget;
	Money deviationPatronageAcceptedBudget;
	Money minPatronageAcceptedBudget;
	Money maxPatronageAcceptedBudget;
	
	Money avgPatronageDenniedBudget;
	Money deviationPatronageDenniedBudget;
	Money minPatronageDenniedBudget;
	Money maxPatronageDenniedBudget;
	
}
