package acme.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ToolkitComponent extends AbstractEntity{

	protected static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotBlank
	@Positive
	protected Integer 		multiplicity;
	
	// Relationships ----------------------------------------------------------
	
	@ManyToOne(optional=false)
	@NotNull
	protected Component		component;
	
	@ManyToOne(optional=false)
	@NotNull
	protected Toolkit 		toolkit;

	
	
	
	
	
}
