package com.example.tbike;

import org.json.JSONObject;

import java.io.IOException;

public class KeyManager {
    private static KeyManager instance = null;
    private String auth_Key = "";

    public static KeyManager getInstance() {
        if (instance == null) {
            instance = new KeyManager();
        }
        return instance;
    }

    public String getAuth_Key() {
        return auth_Key;
    }

    public synchronized void createKey(int problem){

        try {
            JSONObject jsonObject = Connection.getInstance().start(problem);
            auth_Key = jsonObject.getString("auth_key");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
