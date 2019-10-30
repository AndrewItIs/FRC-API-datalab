package org.shrewsburyrobotics.datalab.datawrite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.google.gson.Gson;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.shrewsburyrobotics.datalab.datafetch.requestTypes;


public class frcJsonParser {
Gson gson = new Gson();
public JSONArray docs;
public JSONObject jsonResponse;
public File file = new File("C://Users//Andrew//Documents//testcsv");
public File mTestFile;
public String csv;
public String flattenedJson, test;
public requestTypes requestType;

    public frcJsonParser(String mBody, requestTypes mRequestTypes) {
        //convert response body into JSON
            jsonResponse = new JSONObject(mBody);

            switch(mRequestTypes){
            case MATCHES:
                docs = jsonResponse.getJSONArray("Matches");
                break;

            case AWARDS:
                docs = jsonResponse.getJSONArray("Awards");
                break;

            case EVENTS:
                 docs = jsonResponse.getJSONArray("Events");
                break;

            case TEAMS:
                 docs = jsonResponse.getJSONArray("Teams");
                break;

            case SCORES:
                docs = jsonResponse.getJSONArray("MatchScores");
                break;

            default:
                //defaults to matches
                docs = jsonResponse.getJSONArray("Matches");
        }

            csv = CDL.toString(docs);
           // flattenedJson = JsonFlattener.flatten(csv);

            requestType = mRequestTypes;

    }
}