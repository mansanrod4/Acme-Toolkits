package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import acme.components.validators.MoneyConstraint;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
public class Component extends AbstractEntity{

	private static final long serialVersionUID = 6132121623045095098L;
	
	@NotBlank
	@Size(min=0,max=101)
	protected String name;
	
	@Column(unique=true)
	@NotNull
	@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;
	
	@NotBlank
	@Size(min=0,max=101)
	protected String technology;
	
	@NotBlank
	@Size(min=0,max=256)
	protected String description;
	
	@NotNull
	@MoneyConstraint(allowsZero=false, allowsNegative=false)
	protected Money retailPrice;
	
	protected String link;

}
