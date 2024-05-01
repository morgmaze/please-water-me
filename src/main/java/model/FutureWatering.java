package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Represents a plant watering reminder that will be sent.
 * 
 * @author morganmazer
 *
 */
@Entity
public class FutureWatering {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	// a plant only has one future watering at a time
	@OneToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Plant plant;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reminderDate;
	

	public FutureWatering(Plant plant, Date reminderDate) {
		super();
		this.plant = plant;
		this.reminderDate = reminderDate;
	}
	
	public FutureWatering() {
		
	}

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

	public Date getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(Date reminderDate) {
		this.reminderDate = reminderDate;
	}

	@Override
	public String toString() {
		return "FutureWatering [id=" + id + ", plant=" + plant + ", reminderDate=" + reminderDate + "]";
	}

}
