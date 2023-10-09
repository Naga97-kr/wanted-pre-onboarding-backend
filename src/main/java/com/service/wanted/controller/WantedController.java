package com.service.wanted.controller;

import com.service.wanted.domain.Recruitment;
import com.service.wanted.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WantedController {

    private final RecruitmentService recruitmentService;

    @Autowired
    public WantedController(RecruitmentService recruitmentService) {
        this.recruitmentService = recruitmentService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @PostMapping("/recruitment")
    public void saveRecruitment(@RequestBody Recruitment recruitment) {
        recruitmentService.save(recruitment);
    }


}
