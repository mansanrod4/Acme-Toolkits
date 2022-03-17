
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Patronage extends AbstractEntity {


	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@NotNull
	@Enumerated(EnumType.STRING)
	protected PatronageStatus	status;

	@NotNull
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String			code;

	@NotBlank
	@Length(max = 256)
	@Column(name = "legal_stuff")
	protected String			legalStuff;

	protected Money			budget;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	protected Date			startDate;
	
//	@NotNull
//	@Temporal(TemporalType.DATE)
//	protected Date			finalDate;
//	
	@NotNull
	@Min(1) //num meses
	protected Integer			period;	
	
	@URL
	@Column(name = "further_information")
	protected String			furtherInformation;

	// Derived attributes -----------------------------------------------------

	
//	@Min(1)
//	public Long period() {
//
//		return this.finalDate.getTime()-this.startDate.getTime();
//		
//	}

	



}
