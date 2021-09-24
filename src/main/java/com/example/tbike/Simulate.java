package com.example.tbike;

public class Simulate {
    private String status;
    private Integer time;
    private Integer failed_requests_count;
    private String distance;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getFailed_requests_count() {
        return failed_requests_count;
    }

    public void setFailed_requests_count(Integer failed_requests_count) {
        this.failed_requests_count = failed_requests_count;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
