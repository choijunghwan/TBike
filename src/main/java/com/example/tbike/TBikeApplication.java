package com.example.tbike;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TBikeApplication {

    private static int problem = 1;
    public static void main(String[] args) throws IOException {
        SpringApplication.run(TBikeApplication.class, args);


        // 데이터 초기화
        int time = -1;

        Scenario scenario = getScenario();

        List<Location> locationList = getLocationList(scenario);
        List<Truck> truckList = getTruckList(scenario);

        start(problem);

        while (true) {
            time++;

            if (time > 720) {
                score();
                break;
            }
            //자전거 대여소에 있는 자전거 수
            locationList = location(locationList);

            //정시 분마다 사용자가 대여 요청을 할 때 받은 정보를 이용해, 사용 시간이 다 된 자전거를 반납한다.

            //정시 분마다 사용자가 보낸 새로운 대여 요청을 순서대로 처리한다.

            //죠르디의 지시에 따라 트럭을 운행한다.

        }



    }

    private static void score() throws IOException {
        System.out.println(">>>> api.score()");
        String score = Connection.getInstance().score();

        System.out.println(Float.parseFloat(score));
    }

    private static List<Location> location(List<Location> locationList) throws IOException {
        System.out.println(">>>> api.start()");
        JSONObject responseJson = Connection.getInstance().location();

        JSONParser parser = new JSONParser();
        try {
            locationList.addAll(parser.getLocationsFromAPI(responseJson));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return locationList;
    }

    private static List<Truck> getTruckList(Scenario scenario) {
        List<Truck> truckList = new ArrayList<>();

        for (int i = 0; i < scenario.getTruckCnt(); i++) {
            truckList.add(new Truck(i, 0, 0));
        }
        return truckList;
    }

    private static List<Location> getLocationList(Scenario scenario) {
        List<Location> locationList = new ArrayList<>();
        int totalSize = scenario.getSize() * scenario.getSize();

        for (int i = 0; i < totalSize; i++) {
            locationList.add(new Location(i, scenario.getBikeCnt() / totalSize));
        }
        return locationList;
    }

    private static Scenario getScenario() {
        if (problem == 1) {
            return new Scenario(5, 100, 5);
        }

        return new Scenario(60, 10800, 10);
    }

    private static void start(int problem) {
        System.out.println(">>>> api.start()");
        KeyManager.getInstance().createKey(problem);
        System.out.println("auth_key : " + KeyManager.getInstance().getAuth_Key());
    }

}
