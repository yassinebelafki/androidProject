package com.androidproject.models.Laureate;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Laureate {
    private String name;
    private Integer age;
    private String email;
    private String phone;
    private String training;
    private String city;
    private List<LaureateExperience> laureateExperiences;
    private List<LaureateInterests> laureateInterests;
    private List<LaureateSkill> laureateSkills;

}
