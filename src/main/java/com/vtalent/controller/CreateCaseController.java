package com.vtalent.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vtalent.binding.CreateCaseResponse;
import com.vtalent.service.DcService;

@RestController
public class CreateCaseController {

	@Autowired
	private DcService dcService;
	
	@GetMapping("/case/{appId}")
	public ResponseEntity<CreateCaseResponse> createCase(@PathVariable Integer appId) {
		Long loadCaseNum = dcService.loadCaseNum(appId);

		Map<Integer, String> plansMap = dcService.getPlanNames();

		CreateCaseResponse response = new CreateCaseResponse();
		response.setCaseNum(loadCaseNum);
		response.setPlanNames(plansMap);

		return new ResponseEntity<>(response,HttpStatus.OK);

	}

}
