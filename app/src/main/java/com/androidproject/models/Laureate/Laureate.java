package com.androidproject.models.Laureate;

import java.util.List;

public class Laureate {
    private String name;
    private String email;
    private String phone;
    private String training;
    private String city;
    private List<LaureateExperience> laureateExperiences;
    private List<LaureateInterests> laureateInterests;
    private List<LaureateSkill> laureateSkills;

    public Laureate() {
    }

    public Laureate(String name, String email, String phone, String training, String city, List<LaureateExperience> laureateExperiences, List<LaureateInterests> laureateInterests, List<LaureateSkill> laureateSkills) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.training = training;
        this.city = city;
        this.laureateExperiences = laureateExperiences;
        this.laureateInterests = laureateInterests;
        this.laureateSkills = laureateSkills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<LaureateExperience> getLaureateExperiences() {
        return laureateExperiences;
    }

    public void setLaureateExperiences(List<LaureateExperience> laureateExperiences) {
        this.laureateExperiences = laureateExperiences;
    }

    public List<LaureateInterests> getLaureateInterests() {
        return laureateInterests;
    }

    public void setLaureateInterests(List<LaureateInterests> laureateInterests) {
        this.laureateInterests = laureateInterests;
    }

    public List<LaureateSkill> getLaureateSkills() {
        return laureateSkills;
    }

    public void setLaureateSkills(List<LaureateSkill> laureateSkills) {
        this.laureateSkills = laureateSkills;
    }
}
