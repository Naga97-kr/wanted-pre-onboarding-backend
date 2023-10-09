package com.service.wanted.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityScan
@Table(name = "recruitment")
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "company_id", nullable = false)
    private String company_id;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "reward", nullable = false)
    private String reward;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "skill", nullable = false)
    private String skill;
}
