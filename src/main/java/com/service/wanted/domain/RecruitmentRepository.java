package com.service.wanted.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    List<Recruitment> findAllByCompany(Company company);
    List<Recruitment> findAllByPositionContains(String keyword);
}
