package com.service.wanted.service;

import com.service.wanted.domain.Recruitment;
import com.service.wanted.domain.RecruitmentRepository;
import com.service.wanted.dto.RecruitmentDetailDto;
import com.service.wanted.dto.RecruitmentDto;
import com.service.wanted.dto.RecruitmentListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public RecruitmentService(RecruitmentRepository recruitmentRepository){
        this.recruitmentRepository = recruitmentRepository;
    }

    // 채용 공고 등록
    public void save(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
    }

    // 채용 공고 리스트
    public List<RecruitmentListDto> getRecruitmentList() {
        List<RecruitmentListDto> list = new ArrayList<>();
        List<Recruitment> recruitmentList = recruitmentRepository.findAll();
        for (Recruitment recruitment : recruitmentList) {
            list.add(RecruitmentListDto.builder()
                  .id(recruitment.getId())
                  .companyName(recruitment.getCompanyName())
                  .country(recruitment.getCountry())
                  .city(recruitment.getCity())
                  .position(recruitment.getPosition())
                  .reward(recruitment.getReward())
                  .skill(recruitment.getSkill())
                  .build());
        }
        return list;
    }

    // 채용 공고 상세
    public RecruitmentDetailDto getRecruitmentDetail(Long id) {
        Recruitment recruitment = recruitmentRepository.findById(id).get();
        List<Recruitment> recruitmentList = recruitmentRepository.findAllByCompanyId(recruitment.getCompanyId());
        List<String> list = new ArrayList<>();
        for (Recruitment recruitment1 : recruitmentList) {
            list.add(recruitment1.getPosition());
        }
        return RecruitmentDetailDto.builder()
                .id(recruitment.getId())
                .companyName(recruitment.getCompanyName())
                .country(recruitment.getCountry())
                .city(recruitment.getCity())
                .position(recruitment.getPosition())
                .reward(recruitment.getReward())
                .skill(recruitment.getSkill())
                .details(recruitment.getDetails())
                .anotherRecruitment(list)
                .build();
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
