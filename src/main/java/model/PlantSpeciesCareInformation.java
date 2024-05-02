package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PlantSpeciesCareInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String species;

	private String commonName;

	private int minDaysToWater;

	private int maxDaysToWater;

	private String lightLevel;

	private int minTemperatureF;

	private int maxTemperatureF;

	private int minTemperatureC;

	private int maxTemperatureC;

	private boolean safeForDogs;

	private boolean safeForCats;
	
	
	public PlantSpeciesCareInformation(String species, String commonName, int minDaysToWater, int maxDaysToWater,
			String lightLevel, int minTemperatureF, int maxTemperatureF, int minTemperatureC, int maxTemperatureC,
			boolean safeForDogs, boolean safeForCats) {
		super();
		this.species = species;
		this.commonName = commonName;
		this.minDaysToWater = minDaysToWater;
		this.maxDaysToWater = maxDaysToWater;
		this.lightLevel = lightLevel;
		this.minTemperatureF = minTemperatureF;
		this.maxTemperatureF = maxTemperatureF;
		this.minTemperatureC = minTemperatureC;
		this.maxTemperatureC = maxTemperatureC;
		this.safeForDogs = safeForDogs;
		this.safeForCats = safeForCats;
	}

	public PlantSpeciesCareInformation() {
		
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

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public int getMinDaysToWater() {
		return minDaysToWater;
	}

	public void setMinDaysToWater(int minDaysToWater) {
		this.minDaysToWater = minDaysToWater;
	}

	public int getMaxDaysToWater() {
		return maxDaysToWater;
	}

	public void setMaxDaysToWater(int maxDaysToWater) {
		this.maxDaysToWater = maxDaysToWater;
	}

	public String getLightLevel() {
		return lightLevel;
	}

	public void setLightLevel(String lightLevel) {
		this.lightLevel = lightLevel;
	}

	public int getMinTemperatureF() {
		return minTemperatureF;
	}

	public void setMinTemperatureF(int minTemperatureF) {
		this.minTemperatureF = minTemperatureF;
	}

	public int getMaxTemperatureF() {
		return maxTemperatureF;
	}

	public void setMaxTemperatureF(int maxTemperatureF) {
		this.maxTemperatureF = maxTemperatureF;
	}

	public int getMinTemperatureC() {
		return minTemperatureC;
	}

	public void setMinTemperatureC(int minTemperatureC) {
		this.minTemperatureC = minTemperatureC;
	}

	public int getMaxTemperatureC() {
		return maxTemperatureC;
	}

	public void setMaxTemperatureC(int maxTemperatureC) {
		this.maxTemperatureC = maxTemperatureC;
	}

	public boolean isSafeForDogs() {
		return safeForDogs;
	}

	public void setSafeForDogs(boolean safeForDogs) {
		this.safeForDogs = safeForDogs;
	}

	public boolean isSafeForCats() {
		return safeForCats;
	}

	public void setSafeForCats(boolean safeForCats) {
		this.safeForCats = safeForCats;
	}

	@Override
	public String toString() {
		return "PlantSpeciesCareInformation [id=" + id + ", species=" + species + ", commonName=" + commonName
				+ ", minDaysToWater=" + minDaysToWater + ", maxDaysToWater=" + maxDaysToWater + ", lightLevel="
				+ lightLevel + ", minTemperatureF=" + minTemperatureF + ", maxTemperatureF=" + maxTemperatureF
				+ ", minTemperatureC=" + minTemperatureC + ", maxTemperatureC=" + maxTemperatureC + ", safeForDogs="
				+ safeForDogs + ", safeForCats=" + safeForCats + "]";
	}

}
