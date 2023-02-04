package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.entity.VaccinationCenter;
import com.example.model.Citizen;
import com.example.model.RequiredResponse;
import com.example.repository.VaccinationCenterRepo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {

	@Autowired
	VaccinationCenterRepo centerRepo;
	
	@Autowired
	RestTemplate restTemplate;

	@PostMapping(path = "/add")
	public ResponseEntity<?> addVaccinationCenter(@RequestBody VaccinationCenter newCenter) {
		VaccinationCenter vaccinationCenter = centerRepo.save(newCenter);
		return new ResponseEntity<>(vaccinationCenter, HttpStatus.OK);
	}
	
	@GetMapping("/get/id/{id}")
	@HystrixCommand(fallbackMethod = "handleCitizenServiceDowntime")
	public ResponseEntity<RequiredResponse> getAllDataBasedOnCenterId(@PathVariable Integer id){
		
//		1st get vaccination center details 
		VaccinationCenter center = centerRepo.findById(id).get();
		
//		get all citizen register to vaccination center
		List<Citizen> citizen = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/id/"+id, List.class);
		
		RequiredResponse res = new RequiredResponse();
		res.setCenter(center);
		res.setCitizens(citizen);
		
		return new ResponseEntity<RequiredResponse>(res, HttpStatus.OK);
	}
	
	public ResponseEntity<RequiredResponse> handleCitizenServiceDowntime(@PathVariable Integer id){
		VaccinationCenter center = centerRepo.findById(id).get();
		
		RequiredResponse res = new RequiredResponse();
		res.setCenter(center);
		
		return new ResponseEntity<RequiredResponse>(res, HttpStatus.OK);
	}
}
