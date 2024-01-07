package com.androidproject.models.Laureate;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class LaureateInterests extends Entity{
    private String name;

    public LaureateInterests() {
    }

    public LaureateInterests( String name) {
        this.name = name;
    }

    public LaureateInterests(Integer id, String name) {
        super(id);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaureateInterests interests = (LaureateInterests) o;
        return Objects.equals(name, interests.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
