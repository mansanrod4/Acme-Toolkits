
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

public class PatronageReport extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Past
	protected Date				moment;

	@NotBlank
	@Size(max = 255)
	protected String			memorandum;

	@URL
	protected String			info;

	@NotBlank
	//	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?:[0-9]{4}$")
	@Column(unique = true)
	protected String			sequenceNumber;

	//	@NotNull
	//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	//	protected Integer serialNumber;

	// Derived attributes -----------------------------------------------------

	//	//pattern “〈patronage-code〉:〈serial-number〉
	//	@NotBlank
	//	@Transient
	//	public String getSequenceNumber(){
	//		final String formatString = String.format("%0"+(5-this.serialNumber.toString().length())+"s",this.serialNumber.toString());
	//		final DecimalFormat decimalFormat = new DecimalFormat("0000");
	//		return this.patronage.getCode() +":"+decimalFormat.format(this.serialNumber);
	//	}

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Patronage			patronage;

}
