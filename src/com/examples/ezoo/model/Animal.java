package com.examples.ezoo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Animal{
	
	private long animalID = 0L;
	private String name = "";
	
	private String taxKingdom = "";
	private String taxPhylum = "";
	private String taxClass = "";
	private String taxOrder = "";
	private String taxFamily = "";
	private String taxGenus = "";
	private String taxSpecies = "";
	
	private double height = 0D;
	private double weight = 0D;
	
	private String type = "";
	private String healthStatus = "";
	
	private int feedingScheduleId = FeedingSchedule.getNotFedId();
	
	public Animal(){}

	//TODO backwards compatibility constructor, remove after we are sure all references to animal generation include a feeding schedule
	//Added February 15, 2020
	public Animal(long animalID, String name, String taxKingdom, String taxPhylum, String taxClass, String taxOrder,
			String taxFamily, String taxGenus, String taxSpecies, double height, double weight, String type,
			String healthStatus) {
		super();
		this.animalID = animalID;
		this.name = name;
		this.taxKingdom = taxKingdom;
		this.taxPhylum = taxPhylum;
		this.taxClass = taxClass;
		this.taxOrder = taxOrder;
		this.taxFamily = taxFamily;
		this.taxGenus = taxGenus;
		this.taxSpecies = taxSpecies;
		this.height = height;
		this.weight = weight;
		this.type = type;
		this.healthStatus = healthStatus;
		this.feedingScheduleId = FeedingSchedule.getNotFedId();
	}
	public Animal(long animalID, String name, String taxKingdom, String taxPhylum, String taxClass, String taxOrder,
			String taxFamily, String taxGenus, String taxSpecies, double height, double weight, String type,
			String healthStatus, int feedingScheduleId) {
		super();
		setAnimalID(animalID);
		setName(name);
		setTaxKingdom(taxKingdom);
		setTaxPhylum(taxPhylum);
		setTaxClass(taxClass);
		setTaxOrder(taxOrder);
		setTaxFamily(taxFamily);
		setTaxGenus(taxGenus);
		setTaxSpecies(taxSpecies);
		setHeight(height);
		setWeight(weight);
		setType(type);
		setHealthStatus(healthStatus);
		setFeedingScheduleId(feedingScheduleId);
	}
	public Animal(ResultSet rs){
		super();
		try {
			setAnimalID(rs.getLong("animalid"));
			setName(rs.getString("name"));
			setTaxKingdom(rs.getString("taxkingdom"));
			setTaxPhylum(rs.getString("taxphylum"));
			setTaxClass(rs.getString("taxclass"));
			setTaxOrder(rs.getString("taxorder"));
			setTaxFamily(rs.getString("taxfamily"));
			setTaxGenus(rs.getString("taxgenus"));
			setTaxSpecies(rs.getString("taxspecies"));
			setHeight(rs.getDouble("height"));
			setWeight(rs.getDouble("weight"));
			setType(rs.getString("type"));
			setHealthStatus(rs.getString("healthstatus"));
			setFeedingScheduleId(rs.getInt("feeding_schedule"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public long getAnimalID() {
		return animalID;
	}

	public Animal setAnimalID(long animalID) {
		this.animalID = animalID;
		return this;
	}

	public String getName() {
		return name;
	}

	public Animal setName(String name) {
		this.name = name;
		return this;
	}

	public String getTaxKingdom() {
		return taxKingdom;
	}

	public Animal setTaxKingdom(String taxKingdom) {
		this.taxKingdom = taxKingdom;
		return this;
	}

	public String getTaxPhylum() {
		return taxPhylum;
	}

	public Animal setTaxPhylum(String taxPhylum) {
		this.taxPhylum = taxPhylum;
		return this;
	}

	public String getTaxClass() {
		return taxClass;
	}

	public Animal setTaxClass(String taxClass) {
		this.taxClass = taxClass;
		return this;
	}

	public String getTaxOrder() {
		return taxOrder;
	}

	public Animal setTaxOrder(String taxOrder) {
		this.taxOrder = taxOrder;
		return this;
	}

	public String getTaxFamily() {
		return taxFamily;
	}

	public Animal setTaxFamily(String taxFamily) {
		this.taxFamily = taxFamily;
		return this;
	}

	public String getTaxGenus() {
		return taxGenus;
	}

	public Animal setTaxGenus(String taxGenus) {
		this.taxGenus = taxGenus;
		return this;
	}

	public String getTaxSpecies() {
		return taxSpecies;
	}

	public Animal setTaxSpecies(String taxSpecies) {
		this.taxSpecies = taxSpecies;
		return this;
	}

	public double getHeight() {
		return height;
	}

	public Animal setHeight(double height) {
		this.height = height;
		return this;
	}

	public double getWeight() {
		return weight;
	}

	public Animal setWeight(double weight) {
		this.weight = weight;
		return this;
	}

	public String getType() {
		return type;
	}

	public Animal setType(String type) {
		this.type = type;
		return this;
	}

	public String getHealthStatus() {
		return healthStatus;
	}

	public Animal setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
		return this;
	}
	
	public int getFeedingScheduleId() {
		return this.feedingScheduleId;
	}
	//TODO add a feeding schedule getter that looks up the schedule by  this.feedingScheduleId
//	public FeedingSchedule getFeedingSchedule() {
//	}
	public Animal setFeedingScheduleId(int feedingScheduleId) {
		this.feedingScheduleId = feedingScheduleId;
		return this;
	}

	@Override
	public String toString() {
		return "Animal [animalID=" + animalID + ", name=" + name + ", taxKingdom=" + taxKingdom + ", taxPhylum="
				+ taxPhylum + ", taxClass=" + taxClass + ", taxOrder=" + taxOrder + ", taxFamily=" + taxFamily
				+ ", taxGenus=" + taxGenus + ", taxSpecies=" + taxSpecies + ", height=" + height + ", weight=" + weight
				+ ", type=" + type + ", healthStatus=" + healthStatus + ", feedingScheduleId=" + getFeedingScheduleId() + "]";
	}
	
	
	
}
