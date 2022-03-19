package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkit extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------
	
	@Column(unique=true)
	@NotNull
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")	
	protected String 		code;
	
	
	@NotBlank
	@Length(max=101)
	protected String 		title;
	
	@NotBlank
	@Length(max=256)
	protected String 		description;
	
	@NotBlank
	@Length(max=256)
	protected String 		assemblyNotes;
	
	@URL	
	protected String		info;

	// Derived attributes -----------------------------------------------------
	

	// Relationships ----------------------------------------------------------

	
	
	
}
