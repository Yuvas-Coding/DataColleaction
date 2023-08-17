package com.vtalent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vtalent.binding.Income;
import com.vtalent.service.DcService;

@RestController
public class IncomeRestcontroller {
	
	@Autowired
	private DcService dcService;
	
	@PostMapping("/income")
	public ResponseEntity<Long> saveIncome(@RequestBody Income income){
		
		Long caseNum = dcService.saveIncomeDate(income);
		
		return new ResponseEntity<>(caseNum,HttpStatus.CREATED);
	
		
	}

}
