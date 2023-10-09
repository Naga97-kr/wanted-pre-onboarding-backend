package com.service.wanted.controller;

import com.service.wanted.dto.*;
import com.service.wanted.service.ApplyService;
import com.service.wanted.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class WantedController {

    private final RecruitmentService recruitmentService;
    private final ApplyService applyService;

    @Autowired
    public WantedController(RecruitmentService recruitmentService, ApplyService applyService) {
        this.recruitmentService = recruitmentService;
        this.applyService = applyService;
    }

    // 채용 공고 등록
    @PostMapping("/add/recruitment")
    public ResponseEntity<RecruitmentSaveDto> saveRecruitment(@RequestBody RecruitmentSaveDto recruitmentSaveDto) {
        recruitmentService.save(recruitmentSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(recruitmentSaveDto);
    }

    // 채용 공고 지원
    @PostMapping("/apply/recruitment")
    public ResponseEntity<Boolean> applyRecruitment(@RequestBody ApplyDto applyDto) {
        boolean check = applyService.applyCheck(applyDto.getRecruitmentId(), applyDto.getUserId()); // 공고 지원 여부 확인
        if (!check) { // 지원 여부 없을 시 지원등록
            applyService.save(applyDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(check);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(check);
    }

    // 채용 공고 리스트 확인
    @GetMapping("/list/recruitment")
    public ResponseEntity<List<RecruitmentListDto>> getRecruitmentList() {
        return ResponseEntity.ok(recruitmentService.getRecruitmentList());
    }

    // 채용 공고 상세 페이지 확인
    @GetMapping("/detail/recruitment/{id}")
    public ResponseEntity<RecruitmentDetailDto> getRecruitmentDetail(@PathVariable("id") Long id) {
        return ResponseEntity.ok(recruitmentService.getRecruitmentDetail(id));
    }

    // 회사이름으로 검색
    @GetMapping("/search/recruitment/company")
    public ResponseEntity<List<RecruitmentListDto>> searchNameRecruitment(@RequestParam("keyword") String keyword) throws UnsupportedEncodingException {
        String incording = new String(keyword.getBytes("8859_1"), "UTF-8");
        return ResponseEntity.ok(recruitmentService.getSearchNameRecruitment(keyword));
    }

    // 포지션으로 검색
    @GetMapping("/search/recruitment/position")
    public ResponseEntity<List<RecruitmentListDto>> searchPositionRecruitment(@RequestParam("keyword") String keyword) throws UnsupportedEncodingException {
        String incording = new String(keyword.getBytes("8859_1"), "UTF-8");
        return ResponseEntity.ok(recruitmentService.getSearchPositionRecruitmentList(keyword));
    }

    // 채용 공고 수정
    @PutMapping("/update/recruitment/{id}")
    public ResponseEntity<RecruitmentUpdateDto> updateRecruitment(@PathVariable("id") Long id, @RequestBody RecruitmentUpdateDto recruitmentDto) {
        recruitmentService.update(id, recruitmentDto);
        return ResponseEntity.ok(recruitmentDto);
    }

    // 채용 공고 삭제
    @DeleteMapping("/delete/recruitment/{id}")
    public void deleteRecruitment(@PathVariable("id") Long id) {
        recruitmentService.delete(id);
    }

}
