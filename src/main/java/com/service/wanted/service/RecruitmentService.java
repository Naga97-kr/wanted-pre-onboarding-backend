package com.service.wanted.service;

import com.service.wanted.domain.Recruitment;
import com.service.wanted.domain.RecruitmentRepository;
import com.service.wanted.dto.RecruitmentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public RecruitmentService(RecruitmentRepository recruitmentRepository, RecruitmentRepository recruitmentRepository1) {
        this.recruitmentRepository = recruitmentRepository1;
    }

    // 채용 공고 등록
    public void save(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
    }

    // 채용 공고 수정
    public void update(Long id, RecruitmentDto recruitmentDto) {
        Recruitment recruit = recruitmentRepository.findById(id).get();
        recruit.setSkill(recruitmentDto.getSkill());
        recruit.setDetails(recruitmentDto.getDetails());
        recruit.setReward(recruitmentDto.getReward());
        recruit.setPosition(recruitmentDto.getPosition());
        recruitmentRepository.save(recruit);
    }


    // 채용 공고 삭제
    public void delete(Long id) {
        recruitmentRepository.deleteById(id);
    }
}
