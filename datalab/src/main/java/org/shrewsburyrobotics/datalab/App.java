package org.shrewsburyrobotics.datalab;

import java.io.IOException;

import org.shrewsburyrobotics.datalab.datafetch.datafetcher;
import org.shrewsburyrobotics.datalab.datafetch.requestTypes;
import org.shrewsburyrobotics.datalab.datafetch.tournementLevel;
import org.shrewsburyrobotics.datalab.datafetch.yearIndex;
import org.shrewsburyrobotics.datalab.datawrite.fileWriter;


public class App {
    private datafetcher mDataFetcher;
    private String responseTest;
    private String responseTest1;
    private fileWriter mFileWriter;
    
    private void runAwards() {
        mDataFetcher = new datafetcher(300, yearIndex.DEEPSPACE, requestTypes.AWARDS, "MABOS");
        mDataFetcher.addParameter("teamNumber", 467);
        try {
        mDataFetcher.sendGet();
        } catch (IOException e) {
            e.printStackTrace();
        }

        responseTest = mDataFetcher.receiveBody();
    }

    private void runMatchScores() {
        mDataFetcher = new datafetcher(300, yearIndex.DEEPSPACE, requestTypes.MATCHES, "MABOS");
        mDataFetcher.addParameter("teamNumber", 467);
        mDataFetcher.addParameter("tournamentlevel", "playoff");
        try {
        mDataFetcher.sendGet();
        } catch (IOException e) {
            e.printStackTrace();
        }

        responseTest = mDataFetcher.receiveBody();
    }

    private void runDetailedScores() {
        mDataFetcher = new datafetcher(300, yearIndex.DEEPSPACE, requestTypes.SCORES, "MABOS", tournementLevel.QUALIFICATIONS);
        mDataFetcher.addParameter("matchNumber", 2);
        try {
        mDataFetcher.sendGet();
        } catch (IOException e) {
            e.printStackTrace();
        }

        responseTest = mDataFetcher.receiveBody();
    }

    private void run() {
        mDataFetcher = new datafetcher(300, yearIndex.DEEPSPACE, requestTypes.SCORES, "MABOS", tournementLevel.QUALIFICATIONS);
        mDataFetcher.addParameter("matchNumber", 2);
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
        mFileWriter = new fileWriter(response, type);
        try {
        mFileWriter.writeToFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    public static void main( String[] args ) {
        App app = new App();
        app.runAwards();
        app.writeToFile(app.recieveBody(), requestTypes.AWARDS);

        app.runMatchScores();
        app.writeToFile(app.recieveBody(), requestTypes.MATCHES);

        app.runDetailedScores();
        app.writeToFile(app.recieveBody(), requestTypes.SCORES);
        
    }
}
