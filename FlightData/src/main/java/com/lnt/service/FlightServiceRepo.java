package com.lnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lnt.model.Flight;
import com.lnt.repo.FlightRepo;

@Service
public class FlightServiceRepo {
	
	@Autowired
	FlightRepo flight ;
	
	public List<Flight> getFlight()
	{
		return flight.getAllFlightDetails();
	}
	
	

}
