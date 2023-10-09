package com.service.wanted.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentDto {
    private String companyName;
    private String country;
    private String city;
    private String position;
    private String reward;
    private String details;
    private String skill;
}
