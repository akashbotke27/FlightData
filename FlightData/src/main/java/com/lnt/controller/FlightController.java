package com.lnt.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lnt.model.Flight;
import com.lnt.model.ResponseType;
import com.lnt.service.FlightServiceRepo;

@RestController
@RequestMapping("/flight")
public class FlightController {
	
	@Autowired
	FlightServiceRepo repo;
	
	
	
	@RequestMapping("/getFlight")
	public List<Flight> getMessage()
	{
		return repo.getFlight();
	}
	
	@RequestMapping("/get-flight-details")
	public ResponseEntity<ResponseType> getFlightDetails()
	{
		ResponseType rt = new ResponseType();
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://iata-and-icao-codes.p.rapidapi.com/airlines"))
				.header("X-RapidAPI-Key", "1444f0ccfamsh348f4b969b2a17cp1694c8jsnb52fae952099")
				.header("X-RapidAPI-Host", "iata-and-icao-codes.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		
		
		try {
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			 
			ObjectMapper mapper = new ObjectMapper();
			Flight[] flights = mapper.readValue(response.body().toString(), Flight[].class);
			rt.setStatus("Found");
			rt.setCode("200");
			rt.setMessage("Requested Flight Data Found");
			rt.setTimeStamp(Calendar.getInstance().getTime());
			rt.setFlights(flights);
			
			return ResponseEntity.ok().body(rt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		rt.setStatus("Not Found");
		rt.setCode("404");
		rt.setMessage("No Record Found" );
		rt.setTimeStamp(Calendar.getInstance().getTime());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rt);
		
		
	}
	
	@RequestMapping("/search") 
	public ResponseEntity<ResponseType> getFlightDetail( @RequestParam(name = "iata_code",required = true) String iataCode)
	{
		HttpResponse<String> response = null;
		String result = "";
		Flight flights[] = null;
		String url = "https://iata-and-icao-codes.p.rapidapi.com/airline?iata_code="+iataCode;
		ResponseType rt = new ResponseType();
		
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url))
					.header("X-RapidAPI-Key", "1444f0ccfamsh348f4b969b2a17cp1694c8jsnb52fae952099")
					.header("X-RapidAPI-Host", "iata-and-icao-codes.p.rapidapi.com")
					.method("GET", HttpRequest.BodyPublishers.noBody())
					.build();
			
			 response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			 ObjectMapper mapper = new ObjectMapper();
			 result = response.body().toString();
			 if(! result.isBlank())
				{
				 flights = mapper.readValue(result, Flight[].class);
			     System.out.println(flights[0].toString());
				}
			 
		} catch (IOException | InterruptedException e) {
			
			e.printStackTrace();
		} 
		result = response.body().toString();
		System.out.println("result "+result);
		
		rt.setStatus("Found");
		rt.setCode("200");
		rt.setMessage("Requested Flight Data Found for iata_code "+iataCode );
		rt.setTimeStamp(Calendar.getInstance().getTime());
		rt.setFlights(flights);
		
		
		if(! response.body().isBlank())
			return ResponseEntity.ok().body(rt);
		
		
		
		//creating user defined Response if required not found
		
		rt.setStatus("Not Found");
		rt.setCode("404");
		rt.setMessage("No Data Found For Requested Flight iata Code "+iataCode );
		rt.setTimeStamp(Calendar.getInstance().getTime());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rt);
	}

}
