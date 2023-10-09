package com.service.wanted.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {
    boolean existsByRecruitmentIdAndUserId(Long repositoryId, Long userId);
}
