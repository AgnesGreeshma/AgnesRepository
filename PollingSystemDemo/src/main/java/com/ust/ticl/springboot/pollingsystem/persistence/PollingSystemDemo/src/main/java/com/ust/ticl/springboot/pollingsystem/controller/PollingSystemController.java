package com.ust.ticl.springboot.pollingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ust.ticl.springboot.pollingsystem.service.PollingSystemService;
import com.ust.ticl.springboot.pollingsystem.web.bo.ActualPollingDetails;
import com.ust.ticl.springboot.pollingsystem.web.bo.PollingDetails;
import com.ust.ticl.springboot.pollingsystem.web.model.ActualPolling;
import com.ust.ticl.springboot.pollingsystem.web.model.BaseResponse;
import com.ust.ticl.springboot.pollingsystem.web.model.PollingDetail;


@RestController

@RequestMapping("/pollingsystem")
public class PollingSystemController {
	
	@Autowired
	private PollingSystemService pollingSystemService;
	
	@PostMapping("/createPolling")
	public BaseResponse createPolling(@RequestBody PollingDetails pollingDetails) {
		//Hard-coding userName for the time being
		pollingDetails.setPollingUser("Romy");
		BaseResponse baseResponse = pollingSystemService.createPolling(pollingDetails);
		return baseResponse;
	}
	
	@PutMapping("/updatePolling")
	public BaseResponse updatePolling(@RequestBody PollingDetails pollingDetails) {
		//Hard-coding userName for the time being
		pollingDetails.setPollingUser("Romy");
		BaseResponse baseResponse = pollingSystemService.createPolling(pollingDetails);
		return baseResponse;
	}	
	
	@GetMapping("/retrievePolling")
	public ResponseEntity<PollingDetail> retrievePollingById(@RequestBody PollingDetails pollingDetails) {
		PollingDetail pollingDetail = pollingSystemService.getPollingById(pollingDetails.getPollId()); 
		return new ResponseEntity<PollingDetail>(pollingDetail, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletePolling")
	public BaseResponse deletePollingById(@RequestBody PollingDetails pollingDetails) {
		BaseResponse baseResponse = pollingSystemService.deletePollingById(pollingDetails.getPollId()); 
		return baseResponse;
	}
	
	@PostMapping("/saveActualPolling")
	public BaseResponse saveActualPolling(@RequestBody ActualPollingDetails actualPollingDetails) {
		//Hard-coding userName for the time being
		actualPollingDetails.setPollingUser("Romy");
		BaseResponse baseResponse = pollingSystemService.saveActualPolling(actualPollingDetails);
		return baseResponse;
	}
	
	@GetMapping("/pollingResult")
	public ResponseEntity<PollingDetail> fetchPollingResult(@RequestBody ActualPollingDetails actualPollingDetails) {
		ActualPollingDetails result = pollingSystemService.fetchPollingResult(actualPollingDetails.getPollId()); 
		return null;
	}
	
}
