package com.service.wanted.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentDetailDto {
    private Long id;
    private String company_name;
    private String country;
    private String city;
    private String position;
    private String reward;
    private String skill;
    private String details;
}
