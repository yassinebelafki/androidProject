package com.androidproject.models.Laureate;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public class Laureate {

    String laureateId;
    private String name;
    private Integer age;
    private String email;
    private String phone;
    private String training;
    private String city;
    private List<LaureateExperience> laureateExperiences;
    private List<LaureateInterests> laureateInterests;
    private List<LaureateSkill> laureateSkills;

    public Laureate() {
    }

    public Laureate(String name, Integer age, String email, String phone, String training, String city,
                    List<LaureateExperience> laureateExperiences, List<LaureateInterests> laureateInterests,
                    List<LaureateSkill> laureateSkills) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.training = training;
        this.city = city;
        this.laureateExperiences = laureateExperiences;
        this.laureateInterests = laureateInterests;
        this.laureateSkills = laureateSkills;
    }
    public Laureate(String laureateId ,  String name, Integer age, String email, String phone, String training, String city,
                    List<LaureateExperience> laureateExperiences, List<LaureateInterests> laureateInterests,
                    List<LaureateSkill> laureateSkills) {
        this.laureateId = laureateId;
        this.name = name;
        this.age = age;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getLaureateId() {
        return laureateId;
    }

    public void setLaureateId(String laureateId) {
        this.laureateId = laureateId;
    }

    @Override
    public String toString() {
        return "Laureate{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", training='" + training + '\'' +
                ", city='" + city + '\'' +
                ", laureateExperiences=" + laureateExperiences +
                ", laureateInterests=" + laureateInterests +
                ", laureateSkills=" + laureateSkills +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laureate laureate = (Laureate) o;
        return Objects.equals(name, laureate.name) && Objects.equals(age, laureate.age) &&
                Objects.equals(email, laureate.email) && Objects.equals(phone, laureate.phone) &&
                Objects.equals(training, laureate.training) && Objects.equals(city, laureate.city) &&
                Objects.equals(laureateExperiences, laureate.laureateExperiences) &&
                Objects.equals(laureateInterests, laureate.laureateInterests) && Objects.equals(laureateSkills, laureate.laureateSkills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, email, phone, training, city, laureateExperiences, laureateInterests, laureateSkills);
    }
}
