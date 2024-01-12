package com.androidproject.models.Laureate;

import java.util.Objects;



public class LaureateSkill extends Entity{

    private String type;
    private String name;

    public LaureateSkill(Integer id,String type, String name) {
        super(id);
        this.type = type;
        this.name = name;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaureateSkill that = (LaureateSkill) o;
        return Objects.equals(type, that.type) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }
}
