package com.example.tbike;

public class Truck {
    private Integer id;
    private Integer location_id;
    private Integer loaded_bikes_count;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public Integer getLoaded_bikes_count() {
        return loaded_bikes_count;
    }

    public void setLoaded_bikes_count(Integer loaded_bikes_count) {
        this.loaded_bikes_count = loaded_bikes_count;
    }
}
