package acme.entities.tool;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Tool extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Length(max = 101)
	@NotBlank
	protected String name;
	
	@Pattern(regexp = "[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@NotNull
	@Column(unique = true)
	protected String code;
	
	@NotBlank
	@Length(max = 101)
	protected String technology;
	
	@NotBlank
	@Length(max = 256)
	protected String description;
	
	@Valid
	@NotNull
	protected  Money retailPrice;
	
	@URL
	@Column(name = "further_information")
	protected String furtherInformation;

	// Derived attributes -----------------------------------------------------

	
	
	// Relationships ----------------------------------------------------------


	
}
