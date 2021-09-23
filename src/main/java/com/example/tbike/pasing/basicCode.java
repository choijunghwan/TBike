package com.example.tbike.pasing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class basicCode {

    public static void main(String[] args) throws IOException, ParseException {
        Map<String, Object> getmap = sentHttpGet("https://httpbin.org/get");

        System.out.println(getmap.get("origin"));

        HashMap<String, Object> requstMap = new HashMap<String, Object>();

        // body에 데이터 설정
        requstMap.put("DATA", "DATA");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(requstMap);
        Map<String, Object> postmap = sentHttpPost("https://httpbin.org/post",json);
        System.out.println(postmap.get("origin"));


    }

    //curl -X GET "https://httpbin.org/get" -H "accept: application/json"

    /**
     * {
     *   "args": {},
     *   "headers": {
     *     "Accept": "application/json",
     *     "Accept-Encoding": "gzip, deflate, br",
     *     "Accept-Language": "ko-kr",
     *     "Host": "httpbin.org",
     *     "Referer": "https://httpbin.org/",
     *     "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.2 Safari/605.1.15",
     *     "X-Amzn-Trace-Id": "Root=1-5f69f1db-66dbc54a39cc623b37de9d95"
     *   },
     *   "origin": "59.28.204.76",
     *   "url": "https://httpbin.org/get"
     * }
     */

    /**
     * access-control-allow-credentials: true
     * access-control-allow-origin: *
     * content-length: 493
     * content-type: application/json
     * date: Tue, 22 Sep 2020 12:45:15 GMT
     * server: gunicorn/19.9.0
     */

    /**
     * Post 요청
     *
     * @throws ParseException
     */
    public static Map<String, Object> sentHttpPost(String target,String jsonValue ) throws IOException, ParseException {

        URL url = new URL(target);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept-Chareset", "UTF-8");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);

        OutputStream os = conn.getOutputStream();
        os.write(jsonValue.getBytes("UTF-8"));
        os.flush();


        StringBuilder result = new StringBuilder();
        String inputLine = null;

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        while ((inputLine = in.readLine()) != null) {
            result.append(inputLine);
        }
        conn.disconnect();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(result.toString());
        JSONObject json = (JSONObject) obj;

        return getMapFromJsonObject(json);
    }

    /**
     * Get 요청에 사용
     *
     * @throws ParseException
     */
    public static Map<String, Object> sentHttpGet(String target) throws IOException, ParseException{
        URL url = new URL(target);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);  //서버에서 온 데이터를 출력 할수 있는 상태인지 여부를 확인
        conn.setRequestMethod("GET");  //요청 방식 설정
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/xml");
        conn.setConnectTimeout(10000);  //서버 연결 제한 시간
        conn.setReadTimeout(10000);  // 서버 연결 후 데이터 read 제한 시간

        StringBuilder result = new StringBuilder();
        String inputLine = null;

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        while ((inputLine = in.readLine()) != null) {
            result.append(inputLine);
        }
        conn.disconnect();
        JSONParser parser = new JSONParser();
        Object object = parser.parse(result.toString());
        JSONObject json = (JSONObject) object;

        return getMapFromJsonObject(json);
    }

    /**
     * JsonObject를 Map<String, String>으로 변환한다.
     *
     * @param jsonObj JSONObject.
     * @return
     */
    private static Map<String, Object> getMapFromJsonObject(JSONObject jsonObj) {
        Map<String, Object> map = null;

        try {
            map = new ObjectMapper().readValue(jsonObj.toJSONString(), Map.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
