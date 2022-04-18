package acme.entities.patronages;

import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class PatronageReport extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Past
	protected Date moment;
	
	@NotBlank
	@Size(max=255)
	protected String memorandum;
	
	@URL
	protected String info;
//	
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected Integer serialNumber;
//	
//	
	//pattern “〈patronage-code〉:〈serial-number〉
//	@NotBlank
//	protected String sequenceNumber;


	
	// Derived attributes -----------------------------------------------------
	
//	//pattern “〈patronage-code〉:〈serial-number〉
	@NotBlank
	@Transient
	public String getSequenceNumber(){
//		final String formatString = String.format("%0"+(5-this.serialNumber.toString().length())+"s",this.serialNumber.toString());
		final DecimalFormat decimalFormat = new DecimalFormat("0000");
		
//		final String formatString = String.format("%02d",this.serialNumber);
		return this.patronage.getCode() +":"+decimalFormat.format(this.serialNumber);
	}
//	
	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Patronage patronage;
	

}
