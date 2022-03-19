package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class PatronageReport extends AbstractEntity{/**
	 * 
	 */
	private static final long serialVersionUID = 7607756024273072128L;
	
	@ManyToOne(optional=false)
	protected Patronage patronage;
	
	@NotBlank
	protected String sequenceNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Past
	protected Date creationDate;
	
	@NotBlank
	@Size(max=255)
	protected String memorandum;
	
	@URL
	protected String link;

}
