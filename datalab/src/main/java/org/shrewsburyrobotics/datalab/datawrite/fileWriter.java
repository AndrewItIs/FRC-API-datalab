package org.shrewsburyrobotics.datalab.datawrite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
public File file = new File("C://Users//Team467//Documents//testcsvs");
public String csv;

    public fileWriter(String mBody, requestTypes mRequestTypes) {
        //convert response body into JSON
        String fileSeperator = System.getProperty("file.seperator");
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
                docs = jsonResponse.getJSONArray("Scores");
                break;

            default:
                //defaults to matches
                docs = jsonResponse.getJSONArray("Matches");
        }

            csv = CDL.toString(docs);
         
    }

    public void writeToFile() throws IOException {
            // if(file.createNewFile()){
            //     System.out.println("file created");
            // } else {
            //     System.out.println("file already exists");
            // }

       FileOutputStream out = new FileOutputStream(file + "//test" + 2 + ".csv");
        out.write(csv.getBytes());
        out.close();

    }
}