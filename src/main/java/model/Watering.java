package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Watering {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	// one plant can have many waterings
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "plant_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Plant plant;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateWatered;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public Date getDateWatered() {
		return dateWatered;
	}

	public void setDateWatered(Date dateWatered) {
		this.dateWatered = dateWatered;
	}

	@Override
	public String toString() {
		return "Watering [id=" + id + ", plant=" + plant + ", dateWatered=" + dateWatered + "]";
	}

}
