package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Represents a plant the user has.
 * 
 * @author morganmazer
 *
 */
@Entity
public class Plant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull
	private String species;
	
	@NotNull
	private String location;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateAcquired;
	
	public Plant() {
		
	}

	public Plant(String species, String location, Date dateAcquired) {
		super();
		this.species = species;
		this.location = location;
		this.dateAcquired = dateAcquired;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDateAcquired() {
		return dateAcquired;
	}

	public void setDateAcquired(Date dateAcquired) {
		this.dateAcquired = dateAcquired;
	}

	@Override
	public String toString() {
		return "Plant [id=" + id + ", species=" + species + ", location=" + location
				+ ", dateAcquired=" + dateAcquired + "]";
	}

}
