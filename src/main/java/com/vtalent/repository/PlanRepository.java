package com.vtalent.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vtalent.entity.PlanEntity;

public interface PlanRepository extends JpaRepository<PlanEntity ,Serializable> {

}
