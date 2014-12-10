package rmiClientPackage;


import java.io.Serializable;


public class Tank implements Serializable {
	private String nation;
	private String model;
	private int caliberGun;
	private int enginePower;
	private int weight;
	
	public void setNation (String nation) {
		this.nation=nation;
	}
	public void setModel (String model) {
		this.model=model;
	}
	public void setCaliberGun (int caliberGun) {
		this.caliberGun=caliberGun;
	}
	public void setEnginePower (int enginePower) {
		this.enginePower=enginePower;
	}
	public void setWeight (int weight) {
		this.weight=weight;
	}
	
	public String getNation () {
		return this.nation;
	}
	public String getModel () {
		return this.model;
	}
	public int getCaliberGun () {
		return this.caliberGun;
	}
	public int getEnginePower () {
		return this.enginePower;
	}
	public int getWeight () {
		return this.weight;
	}

}
