package com.androidproject.models.Laureate;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class LaureateExperience {
    private Integer id;
    private String title;
    private String start_date;
    private String end_date;
    private String description;




    public LaureateExperience(Integer id, String title, String start_date, String end_date, String description) {
        this.id = id;
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
    }
    public LaureateExperience(String title, String description, String start_date, String end_date) {
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
    }

    public LaureateExperience() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "LaureateExperience{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaureateExperience that = (LaureateExperience) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(start_date, that.start_date) && Objects.equals(end_date, that.end_date) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, start_date, end_date, description);
    }
}
