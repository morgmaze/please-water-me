package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Plant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	@NotNull
	private String species;
	@NotNull
	private String location;
	private String name;
	private Date dateAcquired;
	
	public Plant() {
		
	}

	public Plant(String species, String location, String name, Date dateAcquired) {
		super();
		this.species = species;
		this.location = location;
		this.name = name;
		this.dateAcquired = dateAcquired;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateAcquired() {
		return dateAcquired;
	}

	public void setDateAcquired(Date dateAcquired) {
		this.dateAcquired = dateAcquired;
	}

	@Override
	public String toString() {
		return "Plant [id=" + id + ", species=" + species + ", location=" + location + ", name=" + name
				+ ", dateAcquired=" + dateAcquired + "]";
	}

}
