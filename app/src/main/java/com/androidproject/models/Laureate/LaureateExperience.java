package com.androidproject.models.Laureate;

public class LaureateExperience {
    private String title;
    private String start_date;
    private String end_date;
    private String description;

    public LaureateExperience(String title, String start_date, String end_date, String description) {
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
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
}
