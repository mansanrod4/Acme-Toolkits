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
	
	EnumMap<PatronageStatus, Integer> numberOfPatronagesByStatus;
	
	Map<Pair<PatronageStatus, String>, Double> averageNumberOfBudgetsByCurrencyAndStatus;
	
	Map<Pair<PatronageStatus, String>, Double> deviationOfBudgetsByCurrencyAndStatus;
	
	Map<Pair<PatronageStatus, String>, Double> minBudgetByCurrencyAndStatus;
	
	Map<Pair<PatronageStatus, String>, Double> maxBudgetByCurrencyAndStatus;
}
