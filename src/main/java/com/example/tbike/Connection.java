package com.example.tbike;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connection {

    public final static String BASE_URL = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users";
    public final static String X_AUTH_TOKEN = "24cdc66f6bd986fd73e9d6613090d7eb";
    private static Connection instance = null;

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    /**
     * GET /trucks
     * Authorization: {auth_key}
     * Content-Type: application/json
     */
    public JSONObject truck() throws IOException {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;


        URL url = new URL(BASE_URL + "/trucks");
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", KeyManager.getInstance().getAuth_Key());
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(5000);

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            responseJson = new JSONObject(sb.toString());
        } else {
            printErrorMessage(responseCode);
        }

        return responseJson;
    }


    /**
     * GET /locations
     * Authorization: {auth_key}
     * Content-Type: application/json
     */
    public JSONObject location() throws IOException {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;


        URL url = new URL(BASE_URL + "/locations");
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", KeyManager.getInstance().getAuth_Key());
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(5000);

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            responseJson = new JSONObject(sb.toString());
        } else {
            printErrorMessage(responseCode);
        }

        return responseJson;
    }

    /**
     * POST /start
     * X-Auth-Token: {X-Auth-Token}
     * Content-Type: application/json
     */
    public JSONObject start(int problem) throws IOException {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;


        URL url = new URL(BASE_URL + "/start");
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("X-Auth-Token", X_AUTH_TOKEN);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(5000);

        //Send post request
        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes("?Problem=" + Integer.toString(problem));
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            responseJson = new JSONObject(sb.toString());

        } else {
            printErrorMessage(responseCode);
        }

        return responseJson;
    }

    private void printErrorMessage(int responseCode) {
        if (responseCode == 400) {
            System.out.println("400 :: Parameter가 잘못됨");
        } else if (responseCode == 401) {
            System.out.println("401 :: 인증을 위한 Header가 잘못됨");
        } else if (responseCode == 500) {
            System.out.println("500 :: 서버 에러, 채팅으로 문의 요망");
        }
    }
}
