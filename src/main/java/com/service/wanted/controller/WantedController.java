package com.service.wanted.controller;

import com.service.wanted.domain.Recruitment;
import com.service.wanted.dto.RecruitmentDto;
import com.service.wanted.dto.RecruitmentListDto;
import com.service.wanted.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WantedController {

    private final RecruitmentService recruitmentService;

    @Autowired
    public WantedController(RecruitmentService recruitmentService) {
        this.recruitmentService = recruitmentService;
    }

    // 채용 공고 등록
    @PostMapping("/add/recruitment")
    public ResponseEntity<Recruitment> saveRecruitment(@RequestBody Recruitment recruitment) {
        recruitmentService.save(recruitment);
        return ResponseEntity.ok(recruitment);
    }

    // 채용 공고 리스트 확인
    @GetMapping("/list/recruitment")
    public ResponseEntity<List<RecruitmentListDto>> getRecruitmentList() {
        return ResponseEntity.ok(recruitmentService.getRecruitmentList());
    }

    // 채용 공고 수정
    @PutMapping("/update/recruitment/{id}")
    public ResponseEntity<RecruitmentDto> updateRecruitment(@PathVariable("id") Long id, @RequestBody RecruitmentDto recruitmentDto) {
        recruitmentService.update(id, recruitmentDto);
        return ResponseEntity.ok(recruitmentDto);
    }

    // 채용 공고 삭제
    @DeleteMapping("/delete/recruitment/{id}")
    public void deleteRecruitment(@PathVariable("id") Long id) {
        recruitmentService.delete(id);
    }
}
