package org.shrewsburyrobotics.datalab.datawrite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.gson.Gson;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.shrewsburyrobotics.datalab.datafetch.requestTypes;


public class fileWriter {
Gson gson = new Gson();
JSONArray docs;
JSONObject jsonResponse;
public File file = new File("C://Users//Andrew//Documents//testcsv");
public File mTestFile;
public String csv;
private String fileName;
public int mFileIndexNumber;

    public fileWriter(String mBody, requestTypes mRequestTypes) {
        //convert response body into JSON
        String fileSeperator = System.getProperty("file.seperator");
            jsonResponse = new JSONObject(mBody);

            switch(mRequestTypes){
            case MATCHES:
                docs = jsonResponse.getJSONArray("Matches");
                fileName = "Matches";
                break;

            case AWARDS:
                docs = jsonResponse.getJSONArray("Awards");
                fileName = "Awards";
                break;

            case EVENTS:
                 docs = jsonResponse.getJSONArray("Events");
                 fileName = "Events";
                break;

            case TEAMS:
                 docs = jsonResponse.getJSONArray("Teams");
                 fileName = "Teams";
                break;

            case SCORES:
                docs = jsonResponse.getJSONArray("MatchScores");
                fileName = "Scores";
                break;

            default:
                //defaults to matches
                docs = jsonResponse.getJSONArray("Matches");
                fileName = "Matches";
        }

            csv = CDL.toString(docs);
         
    }

    public void writeToFile() throws IOException {
        mTestFile = new File("C://Users//Andrew//Documents//testcsv" + "//" + fileName + "_" + mFileIndexNumber + ".csv");
             if(mTestFile.isFile()) {
                mFileIndexNumber++;
                System.out.println("file exists writing new file with " + mFileIndexNumber);
            } else {
                System.out.println("file does not exist");
                if(mFileIndexNumber != 0) {
                    mFileIndexNumber = 0;
                }
            }

            FileOutputStream out = new FileOutputStream(file + "//" + fileName + "_" + mFileIndexNumber + ".csv");
            out.write(csv.getBytes());
            out.close();
    }
}