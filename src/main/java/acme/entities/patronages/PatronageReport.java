package acme.entities.patronages;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
//@Data
//@EqualsAndHashCode(callSuper=true)
//@NoArgsConstructor
public class PatronageReport extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------
	
	@Temporal(TemporalType.DATE)
	@NotNull
	@Past
	@Column(name = "creation_date")
	protected Date creationDate;
	
	@NotBlank
	@Size(max=255)
	protected String memorandum;
	
	@URL
	protected String info;
//	
//	@NotNull
//	@Digits( integer = 4, fraction = 0)
//	protected Integer serialNumber;
//	
//	
	//pattern “〈patronage-code〉:〈serial-number〉
	@NotBlank
	protected String sequenceNumber;

	// Derived attributes -----------------------------------------------------
	
//	//pattern “〈patronage-code〉:〈serial-number〉
//	@NotBlank
//	protected String sequenceNumber(){
//		return "〈"+this.patronage.getCode() +"〉:〈"+this.serialNumber.toString()+"〉";
//	}
//	
	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Patronage patronage;
	

}
