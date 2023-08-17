package com.vtalent.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vtalent.entity.DcIncomeEntity;

public interface DcIncomeRepository extends JpaRepository<DcIncomeEntity , Serializable>{

	public DcIncomeEntity findbycaseNum(Long caseNum);

}
