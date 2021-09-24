package com.example.tbike;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JSONParser {
    public List<Location> getLocationsFromAPI(JSONObject responseJson) {
        List<Location> elevatorList = new ArrayList<>();

        JSONArray locations = responseJson.getJSONArray("locations");
        for (int i = 0; i < locations.length(); i++) {
            JSONObject data = locations.getJSONObject(i);

            Location location = new Location(data.getInt("id"), data.getInt("located_bikes_count"));
            elevatorList.add(location);
        }

        return elevatorList;
    }
}
