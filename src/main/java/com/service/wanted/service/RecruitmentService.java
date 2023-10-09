package com.service.wanted.service;

import com.service.wanted.domain.Recruitment;
import com.service.wanted.domain.RecruitmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public RecruitmentService(RecruitmentRepository recruitmentRepository, RecruitmentRepository recruitmentRepository1) {
        this.recruitmentRepository = recruitmentRepository1;
    }

    public void save(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
    }

}
