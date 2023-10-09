package com.service.wanted.service;

import com.service.wanted.domain.Apply;
import com.service.wanted.domain.ApplyRepository;
import com.service.wanted.dto.ApplyDto;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final ApplyRepository applyRepository;

    public ApplyService(ApplyRepository applyRepository) {
        this.applyRepository = applyRepository;
    }

    public boolean applyCheck(Long repositoryId, Long userId) {
        return applyRepository.existsByRecruitmentIdAndUserId(repositoryId, userId);
    }

    public void save(ApplyDto applyDto) {
        Apply apply = new Apply();
        apply.setRecruitmentId(applyDto.getRecruitmentId());
        apply.setUserId(applyDto.getUserId());
        applyRepository.save(apply);
    }
}
