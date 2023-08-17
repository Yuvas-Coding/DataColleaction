package com.vtalent.service;

import java.util.Map;

import com.vtalent.binding.ChildRequest;
import com.vtalent.binding.DcSummary;
import com.vtalent.binding.Education;
import com.vtalent.binding.Income;
import com.vtalent.binding.PlanSelection;

public interface DcService {
	
	public Long loadCaseNum(Integer appId);
	
	public Map<Integer, String> getPlanNames();
	
	public Long savePlanSelection(PlanSelection planSelection);
	
	public Long saveIncomeDate(Income income);
	
	public Long saveEducation(Education education);
	
	public Long saveChildrens(ChildRequest request);
	
	public DcSummary getSummary(Long caseNumber);
	
	

}
