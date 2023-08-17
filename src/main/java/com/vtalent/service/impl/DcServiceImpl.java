package com.vtalent.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtalent.binding.Child;
import com.vtalent.binding.ChildRequest;
import com.vtalent.binding.DcSummary;
import com.vtalent.binding.Education;
import com.vtalent.binding.Income;
import com.vtalent.binding.PlanSelection;
import com.vtalent.entity.CitizenAppEntity;
import com.vtalent.entity.DcCaseEntity;
import com.vtalent.entity.DcChildrenEntity;
import com.vtalent.entity.DcEducationEntity;
import com.vtalent.entity.DcIncomeEntity;
import com.vtalent.entity.PlanEntity;
import com.vtalent.repository.CitizenAppRepository;
import com.vtalent.repository.DcCaseRepository;
import com.vtalent.repository.DcChildrenRepository;
import com.vtalent.repository.DcEducationRepository;
import com.vtalent.repository.DcIncomeRepository;
import com.vtalent.repository.PlanRepository;
import com.vtalent.service.DcService;

@Service
public class DcServiceImpl implements DcService{
	
	@Autowired
	private CitizenAppRepository citizenAppRepository;
	
	@Autowired
	private DcCaseRepository dcCaseRepository;
	
	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private DcIncomeRepository dcIncomeRepository;
	
	@Autowired
	private DcEducationRepository dcEducationRepository;
	
	@Autowired
	private DcChildrenRepository dcChildrenRepository;

	@Override
	public Long loadCaseNum(Integer appId) {
		// TODO Auto-generated method stub
		Optional<CitizenAppEntity> app = citizenAppRepository.findById(appId);
		if(app.isPresent()) {
			DcCaseEntity entity=new DcCaseEntity();
			
			entity.setAppId(appId);
			DcCaseEntity save = dcCaseRepository.save(entity);
			return save.getCaseNum();
			
		}
		return 0l;
	}

	@Override
	public Map<Integer, String> getPlanNames() {
		// TODO Auto-generated method stub
		List<PlanEntity> findAll = planRepository.findAll();
		
		Map<Integer, String> plansMap=new HashMap<>();
		
		for(PlanEntity entity:findAll) {
			plansMap.put(entity.getPlanId(), entity.getPlanName());
		}
		
		return plansMap;
	}

	@Override
	public Long savePlanSelection(PlanSelection planSelection) {
		// TODO Auto-generated method stub
		Optional<DcCaseEntity> findById = dcCaseRepository.findById(planSelection.getCaseNum());
		
		if (findById.isPresent()) {
			
			DcCaseEntity dcCaseEntity = findById.get();
			
			dcCaseEntity.setPlanId(planSelection.getPlanId());
			
			dcCaseRepository.save(dcCaseEntity);
			
			return planSelection.getCaseNum();
		}
		return null;
	}

	@Override
	public Long saveIncomeDate(Income income) {
		// TODO Auto-generated method stub
		DcIncomeEntity entity = new DcIncomeEntity();
		
		BeanUtils.copyProperties(income, entity);
		
		dcIncomeRepository.save(entity);
		
		return income.getCaseNum();
	}

	@Override
	public Long saveEducation(Education education) {
		// TODO Auto-generated method stub
		DcEducationEntity entity = new DcEducationEntity();
		
		BeanUtils.copyProperties(education, entity);
		
		dcEducationRepository.save(entity);
		
		return education.getCaseNum();
	}

	@Override
	public Long saveChildrens(ChildRequest request) {
		// TODO Auto-generated method stub
		
		List<Child> childs = request.getChilds();
		
		for(Child c:childs) {
			DcChildrenEntity entity = new DcChildrenEntity();
			
			BeanUtils.copyProperties(c, entity);
			
			dcChildrenRepository.save(entity);
		}
		return request.getCaseNum();
	}


	@Override
	public DcSummary getSummary(Long caseNumber) {
		// TODO Auto-generated method stub
		String planName="";
		
		DcIncomeEntity incomeEntity = dcIncomeRepository.findbycaseNum(caseNumber);
		
		DcEducationEntity educationEntity = dcEducationRepository.findByCaseNum(caseNumber);	
		
		List<DcChildrenEntity> childsEntities = dcChildrenRepository.findByCaseNum(caseNumber);
		
		Optional<DcCaseEntity> dcCase = dcCaseRepository.findById(caseNumber);
		
		if (dcCase.isPresent()) {
			Integer planId = dcCase.get().getPlanId();
			Optional<PlanEntity> plan = planRepository.findById(planId);
			if (plan.isPresent()) {
				planName = plan.get().getPlanName();
			}
		}
		//set the data to summary object
		
		DcSummary summary = new DcSummary();
		summary.setPlanName(planName);
		
		Income income=new Income();
		BeanUtils.copyProperties(incomeEntity, income);
		summary.setIncome(income);
		
		Education education = new Education();
		BeanUtils.copyProperties(educationEntity, education);
		summary.setEducation(education);
		
		List<Child> childs = new ArrayList<>();
		
		for(DcChildrenEntity entity:childsEntities) {
			
			Child ch=new Child();
			
			BeanUtils.copyProperties(entity, ch);
			childs.add(ch);
		}
		summary.setChilds(childs);
		
		return summary;
	}

}
