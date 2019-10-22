package org.shrewsburyrobotics.datalab.datawrite;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.*;


public class fileWriter {
Gson gson = new Gson();
JSONObject jsonResponse;

    public fileWriter(ResponseBody mBody) {
        //convert response body into JSON
        try {
            jsonResponse = new JSONObject(mBody.string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}