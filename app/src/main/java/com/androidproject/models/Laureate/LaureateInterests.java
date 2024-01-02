package com.androidproject.models.Laureate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class LaureateInterests {
    private String name;

    public LaureateInterests() {
    }

    public LaureateInterests(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LaureateInterests{" +
                "name='" + name + '\'' +
                '}';
    }
}
