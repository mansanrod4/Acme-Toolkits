package acme.forms;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.patronages.PatronageStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable{

	private static final long serialVersionUID = 1L;
	
	EnumMap<PatronageStatus, Integer> numPatronagesByStatus;
	
	Map<Pair<PatronageStatus, String>, Double> averageBudgetsByStatus;
	
	Map<Pair<PatronageStatus, String>, Double> deviationBudgetsByStatus;
	
	Map<Pair<PatronageStatus, String>, Double> minBudgetByStatus;
	
	Map<Pair<PatronageStatus, String>, Double> maxBudgetByStatus;
}
