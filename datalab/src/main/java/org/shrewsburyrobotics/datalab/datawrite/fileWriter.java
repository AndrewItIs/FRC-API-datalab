package org.shrewsburyrobotics.datalab.datawrite;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.shrewsburyrobotics.datalab.datafetch.requestTypes;

import okhttp3.ResponseBody;


public class fileWriter {
Gson gson = new Gson();
JSONArray docs;
JSONObject jsonResponse;
public File file;
public String csv;

    public fileWriter(ResponseBody mBody, requestTypes mRequestTypes) {
        //convert response body into JSON
        try {
            jsonResponse = new JSONObject(mBody.string());

            switch(mRequestTypes){
            case MATCHES:
                docs = jsonResponse.getJSONArray("matches");
                break;

            case AWARDS:
                docs = jsonResponse.getJSONArray("awards");
                break;

            case EVENTS:
                 docs = jsonResponse.getJSONArray("events");
                break;

            case TEAMS:
                 docs = jsonResponse.getJSONArray("teams");
                break;

            case SCORES:
                docs = jsonResponse.getJSONArray("scores");
                break;

            default:
                //defaults to matches
                docs = jsonResponse.getJSONArray("matches");
        }

            file = new File("/downloads");

            csv = CDL.toString(docs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile() {
        try {
        FileUtils.writeStringToFile(file, csv, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}