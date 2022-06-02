package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.toolkits.Item;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Chimpum")
@Getter
@Setter
public class Chimpum  extends AbstractEntity{

	protected static final long serialVersionUID = 1L;
	
	//d -> digit, w -> text, [yy/mm/dd]
//	@Column(unique = true)
	@Pattern(regexp = "^[0-9]{2}/[0-9]{2}/[0-9]{2}$")
	@NotBlank 
	protected String code;
	
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date moment;
	
	
	@NotBlank
	@Length(max = 100)
	protected String title;
	
	@NotBlank
	@Length(max = 255)
	protected String description;
	
//	protected Period period;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "start_date")
	protected Date				startDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				endDate;
	
	@NotNull
//	@Positive
	protected Money budget; 
	
	@URL
	protected String link;
	

	@NotNull
	@Valid
	@OneToOne(optional = false)
	protected Item			item;

	
}
