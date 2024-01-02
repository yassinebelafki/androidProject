package com.androidproject.models.Laureate;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class LaureateSkill {
    private String type;
    private String name;

    public LaureateSkill(String type, String name) {
       this.type = type;
        this.name = name;
    }

    public LaureateSkill() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LaureateSkill{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
