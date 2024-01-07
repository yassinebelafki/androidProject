package com.androidproject.models.Laureate;

public class Entity {
    protected Integer id;

    public Entity(Integer id) {
        this.id = id;
    }
    public Entity(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
