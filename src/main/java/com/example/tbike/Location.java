package com.example.tbike;

public class Location {

    private Integer id;
    private Integer located_bikes_count;

    public Location(Integer id, Integer located_bikes_count) {
        this.id = id;
        this.located_bikes_count = located_bikes_count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocated_bikes_count() {
        return located_bikes_count;
    }

    public void setLocated_bikes_count(Integer located_bikes_count) {
        this.located_bikes_count = located_bikes_count;
    }
}
