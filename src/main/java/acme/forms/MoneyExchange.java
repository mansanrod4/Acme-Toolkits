
package acme.forms;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MoneyExchange extends AbstractEntity{

	// Query attributes -------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Valid
	public String	sourceCurrency;

	@NotBlank
	public String	targetCurrency;
	
	// Response attributes ----------------------------------------------------

	@Valid
	public Double	rate;

	public LocalDateTime		date;


}
