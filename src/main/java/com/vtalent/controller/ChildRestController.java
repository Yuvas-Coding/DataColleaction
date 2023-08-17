package com.vtalent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vtalent.binding.ChildRequest;
import com.vtalent.binding.DcSummary;
import com.vtalent.service.DcService;

@RestController
public class ChildRestController {
	
	@Autowired
	private DcService dcService;
	
	@PostMapping("/childres")
	public ResponseEntity<DcSummary> saveChilds(@RequestBody ChildRequest request){
		
		Long caseNum = dcService.saveChildrens(request);
		
		DcSummary summary = dcService.getSummary(caseNum);
		
		return new ResponseEntity<>(summary,HttpStatus.OK);
		
	}
	
	
}
