package com.example.tbike;

public class Scenario {
    private int size;
    private int BikeCnt;
    private int TruckCnt;

    public Scenario(int size, int bikeCnt, int truckCnt) {
        this.size = size;
        BikeCnt = bikeCnt;
        TruckCnt = truckCnt;
    }

    public int getSize() {
        return size;
    }

    public int getBikeCnt() {
        return BikeCnt;
    }

    public int getTruckCnt() {
        return TruckCnt;
    }
}
