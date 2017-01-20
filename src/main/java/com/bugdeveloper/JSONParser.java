package com.bugdeveloper;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Nya on 20.01.2017.
 */
public final class JSONParser {

    private static JSONObject jsonObject;

    public static String[][] getFields(String json) {

        jsonObject = new JSONObject(json);

        JSONArray jsonArr = jsonObject.getJSONArray("response");

        String[][] result = new String[jsonArr.length() - 1][2];

        for (int i = 0; i < jsonArr.length() - 1; i++) {
            result[i][0] = jsonArr.getJSONObject(i + 1).getString("text");

            if (jsonArr.getJSONObject(i + 1).has("attachments") && jsonArr.getJSONObject(i + 1).getJSONObject("attachment").has("photo"))
                result[i][1] = jsonArr.getJSONObject(i + 1).getJSONObject("attachment").getJSONObject("photo").getString("src_big");

        }
        return result;
    }

    public static boolean isEmpty() {

        JSONArray jsonArr = jsonObject.getJSONArray("response");

        if (jsonArr.length() < 2) {
            return true;
        }

        return false;
    }

}
