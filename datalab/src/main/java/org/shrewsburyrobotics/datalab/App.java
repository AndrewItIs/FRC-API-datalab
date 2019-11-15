package org.shrewsburyrobotics.datalab;

import java.io.IOException;

import org.json.CDL;
import org.shrewsburyrobotics.datalab.datafetch.datafetcher;
import org.shrewsburyrobotics.datalab.datafetch.requestTypes;
import org.shrewsburyrobotics.datalab.datafetch.tournementLevel;
import org.shrewsburyrobotics.datalab.datafetch.yearIndex;
import org.shrewsburyrobotics.datalab.datawrite.fileIndex;
import org.shrewsburyrobotics.datalab.datawrite.fileWriter;
import org.shrewsburyrobotics.datalab.datawrite.frcJsonParser;
import org.shrewsburyrobotics.datalab.datawrite.frcJsonParser;


public class App {
    private String responseTest;
    private String responseTest1;
    private frcJsonParser mJsonParser;
    private fileWriter mfWriter;
    private datafetcher mDataFetcher = new datafetcher();
    
    private void runAwards() {
        mDataFetcher.addParameter("teamNumber", "467");
        try {
        mDataFetcher.sendGet();
        } catch (IOException e) {
            e.printStackTrace();
        }

        responseTest = mDataFetcher.receiveBody();
    }

    private void runMatchScores() {
        try {
        datafetcher mDataFetcher1 = new datafetcher();
        mDataFetcher1.clearUrl().removeParameters()
            .dataFetch(300, yearIndex.DEEPSPACE, requestTypes.MATCHES, "MABOS")
            .addParameter("teamNumber", "467")
            .addParameter("tournamentlevel", "playoff")
            .sendGet();
        } catch (IOException e) {
            e.printStackTrace();
        }

        responseTest = mDataFetcher.receiveBody();
    }

    private void runDetailedScores() {
        try {
        datafetcher mDataFetcher2 = new datafetcher();
        mDataFetcher2.clearUrl().removeParameters()
            .dataFetch(300, yearIndex.DEEPSPACE, requestTypes.SCORES, "MABOS", tournementLevel.QUALIFICATIONS)
            .addParameter("matchNumber", "2")
            .sendGet();
        } catch (IOException e) {
            e.printStackTrace();
        }

        responseTest = mDataFetcher.receiveBody();
    }

    private void run() {
        mDataFetcher.addParameter("matchNumber", "2");
        try {
        mDataFetcher.sendGet();
        } catch (IOException e) {
            e.printStackTrace();
        }

        responseTest = mDataFetcher.receiveBody();
    }


    private String recieveBody() {
       return responseTest;
    }

    private void writeToFile(String response, requestTypes type) {
        String mName;
        mJsonParser = new frcJsonParser(response, type);

        switch(type){
            case MATCHES:
                mName = "Matches";
            
            case AWARDS:
                mName = "Awards";
            
            case EVENTS:
                mName = "Events";

            case TEAMS:
                mName = "Teams";
            
            case SCORES:
                mName = "Scores";

            default:
                mName = "Null";
        }

        mfWriter = new fileWriter(CDL.toString(mJsonParser.docs), mName);
        try {
        mfWriter.writeToFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    public static void main( String[] args ) {
        App app = new App();
      //  app.runAwards();
      //  app.writeToFile(app.recieveBody(), requestTypes.AWARDS);

        app.runMatchScores();
        app.writeToFile(app.recieveBody(), requestTypes.MATCHES);

        app.runDetailedScores();
        app.writeToFile(app.recieveBody(), requestTypes.SCORES);
        
    }
}
