package com.vtalent.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vtalent.entity.DcChildrenEntity;

public interface DcChildrenRepository extends JpaRepository<DcChildrenEntity, Serializable	> {

	public List<DcChildrenEntity> findByCaseNum(Long caseNumber);

}
