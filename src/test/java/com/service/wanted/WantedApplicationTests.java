package com.service.wanted;

import com.service.wanted.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class WantedApplicationTests {

    @Autowired
    RecruitmentRepository recruitmentRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ApplyRepository applyRepository;

    // 테스트 코드 작성
    @Test
    public void unitTest() {
        // 1. CREATE
        Company company = Company.builder() // 테스트 회사
                .id(1L)
                .name("name")
                .country("country")
                .city("city")
                .recruitmentList(null)
                .build();

        Company saveCompany = companyRepository.save(company); // 저장

        // 저장 후 비교
        Assertions.assertThat(saveCompany.getId()).isEqualTo(company.getId()); 
        Assertions.assertThat(saveCompany.getName()).isEqualTo(company.getName());
        Assertions.assertThat(saveCompany.getCountry()).isEqualTo(company.getCountry());
        Assertions.assertThat(saveCompany.getCity()).isEqualTo(company.getCity());

        // 채용 공고 등록
        Recruitment recruitment = Recruitment.builder()
                .id(1L)
                .skill("skill")
                .reward("reward")
                .details("details")
                .position("position")
                .company(saveCompany)
                .build();

        // 채용 공고 저장
        Recruitment saveRecruitment = recruitmentRepository.save(recruitment);

        // 저장 후 비교
        Assertions.assertThat(saveRecruitment.getId()).isEqualTo(recruitment.getId());
        Assertions.assertThat(saveRecruitment.getSkill()).isEqualTo(recruitment.getSkill());
        Assertions.assertThat(saveRecruitment.getReward()).isEqualTo(recruitment.getReward());
        Assertions.assertThat(saveRecruitment.getDetails()).isEqualTo(recruitment.getDetails());
        Assertions.assertThat(saveRecruitment.getPosition()).isEqualTo(recruitment.getPosition());

        // 2. READ
        // 저장 값 가져오기
        Recruitment selectedRecruitment = recruitmentRepository.findById(1L).orElseThrow(RuntimeException::new);

        // 저장 값과 가져온 값 비교
        Assertions.assertThat(selectedRecruitment.getSkill()).isEqualTo(saveRecruitment.getSkill());
        Assertions.assertThat(selectedRecruitment.getReward()).isEqualTo(saveRecruitment.getReward());
        Assertions.assertThat(selectedRecruitment.getDetails()).isEqualTo(saveRecruitment.getDetails());
        Assertions.assertThat(selectedRecruitment.getPosition()).isEqualTo(saveRecruitment.getPosition());

        // 3. UPDATE
        // 수정 할 값 찾기
        Recruitment foundRecruitment = recruitmentRepository.findById(1L).orElseThrow(RuntimeException::new);

        // 수정하기
        foundRecruitment.setPosition("new position");

        // 수정한대로 저장하기
        Recruitment updatedRecruitment = recruitmentRepository.save(foundRecruitment);

        // 비교
        Assertions.assertThat(updatedRecruitment.getPosition()).isEqualTo("new position");

        // 4. DELETE
        recruitmentRepository.delete(updatedRecruitment);

        // 확인하기
        assertFalse(recruitmentRepository.findById(selectedRecruitment.getId()).isPresent());

    }
}
