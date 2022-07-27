package com.lnt.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {

	@JsonProperty("name")
	private String flightName;
	private String flightId;
	@JsonProperty("iata_code")
	private String ata_code;
	@JsonProperty("icao_code")
	private String cao_code;
	
	@JsonProperty("website")
	private String website;
	
	
	public Flight() {
		super();
		
	}
	
	public Flight(String flightName, String flightId) {
		this.flightName=flightName;
		this.flightId = flightId;
	}

	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	

	public String getAta_code() {
		return ata_code;
	}

	public void setAta_code(String ata_code) {
		this.ata_code = ata_code;
	}

	public String getCao_code() {
		return cao_code;
	}

	public void setCao_code(String cao_code) {
		this.cao_code = cao_code;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	
	@Override
	public String toString() {
		return "Flight [flightName=" + flightName + ", flightId=" + flightId + ", ata_code=" + ata_code + ", cao_code="
				+ cao_code + ", website=" + website + "]";
	}
	
	
	
	
}
