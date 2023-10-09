package com.service.wanted.service;

import com.service.wanted.domain.Company;
import com.service.wanted.domain.CompanyRepository;
import com.service.wanted.domain.Recruitment;
import com.service.wanted.domain.RecruitmentRepository;
import com.service.wanted.dto.RecruitmentDetailDto;
import com.service.wanted.dto.RecruitmentUpdateDto;
import com.service.wanted.dto.RecruitmentListDto;
import com.service.wanted.dto.RecruitmentSaveDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;

    public RecruitmentService(RecruitmentRepository recruitmentRepository, CompanyRepository companyRepository) {
        this.recruitmentRepository = recruitmentRepository;
        this.companyRepository = companyRepository;
    }

    // 채용 공고 등록
    public void save(RecruitmentSaveDto recruitmentSaveDto) {
        Recruitment recruitment = new Recruitment();
        Company company = companyRepository.findById(recruitmentSaveDto.getCompanyId()).get();
        recruitment.setDetails(recruitmentSaveDto.getDetails());
        recruitment.setReward(recruitmentSaveDto.getReward());
        recruitment.setSkill(recruitmentSaveDto.getSkill());
        recruitment.setPosition(recruitmentSaveDto.getPosition());
        recruitment.setCompany(company);
        recruitmentRepository.save(recruitment);
    }

    // 채용 공고 리스트
    public List<RecruitmentListDto> getRecruitmentList() {
        List<RecruitmentListDto> list = new ArrayList<>();
        List<Recruitment> recruitmentList = recruitmentRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        for (Recruitment recruitment : recruitmentList) {
            list.add(RecruitmentListDto.builder()
                    .id(recruitment.getId())
                    .companyName(recruitment.getCompany().getName())
                    .country(recruitment.getCompany().getCountry())
                    .city(recruitment.getCompany().getCity())
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
        Company company = companyRepository.findById(recruitment.getCompany().getId()).get();
        List<Recruitment> recruitmentList = recruitmentRepository.findAllByCompany(company);
        List<String> list = new ArrayList<>();
        for (Recruitment recruitment1 : recruitmentList) {
            list.add(recruitment1.getPosition());
        }
        return RecruitmentDetailDto.builder()
                .id(recruitment.getId())
                .companyName(company.getName())
                .country(company.getCountry())
                .city(company.getCity())
                .position(recruitment.getPosition())
                .reward(recruitment.getReward())
                .skill(recruitment.getSkill())
                .details(recruitment.getDetails())
                .companyRecruitment(list)
                .build();
    }

    // 회사이름으로 검색
    public List<RecruitmentListDto> getSearchNameRecruitment(String keyword) {
        List<RecruitmentListDto> list = new ArrayList<>();
        List<Company> companyList = companyRepository.findAllByNameContains(keyword);
        List<Recruitment> recruitments = new ArrayList<>();
        for (Company company : companyList) {
            recruitments.addAll(recruitmentRepository.findAllByCompany(company));
        }
        for (Recruitment recruitment : recruitments) {
            list.add(RecruitmentListDto.builder()
                    .id(recruitment.getId())
                    .companyName(recruitment.getCompany().getName())
                    .country(recruitment.getCompany().getCountry())
                    .city(recruitment.getCompany().getCity())
                    .position(recruitment.getPosition())
                    .reward(recruitment.getReward())
                    .skill(recruitment.getSkill())
                    .build());
        }
        return list;
    }

    // 포지션으로 검색
    public List<RecruitmentListDto> getSearchPositionRecruitmentList(String keyword) {
        List<RecruitmentListDto> list = new ArrayList<>();
        List<Recruitment> recruitmentList = recruitmentRepository.findAllByPositionContains(keyword);
        for (Recruitment recruitment : recruitmentList) {
            list.add(RecruitmentListDto.builder()
                    .id(recruitment.getId())
                    .companyName(recruitment.getCompany().getName())
                    .country(recruitment.getCompany().getCountry())
                    .city(recruitment.getCompany().getCity())
                    .position(recruitment.getPosition())
                    .reward(recruitment.getReward())
                    .skill(recruitment.getSkill())
                    .build());
        }
        return list;
    }

    // 채용 공고 수정
    public void update(Long id, RecruitmentUpdateDto recruitmentDto) {
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
