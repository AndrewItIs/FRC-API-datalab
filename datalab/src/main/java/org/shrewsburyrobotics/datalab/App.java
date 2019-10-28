package org.shrewsburyrobotics.datalab;

import java.io.IOException;

import org.shrewsburyrobotics.datalab.datafetch.datafetcher;
import org.shrewsburyrobotics.datalab.datafetch.requestTypes;
import org.shrewsburyrobotics.datalab.datafetch.yearIndex;
import org.shrewsburyrobotics.datalab.datawrite.fileWriter;

import okhttp3.ResponseBody;


public class App {
    private datafetcher mDataFetcher;
    private String responseTest;
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

    private String recieveBody() {
       return responseTest;
    }

    private void writeToFile(String response, requestTypes type) {
        mFileWriter = new fileWriter(response, type);
        mFileWriter.writeToFile();
    }


    public static void main( String[] args ) {
        App app = new App();
        app.runAwards();
        //app.writeToFile(app.recieveBody(), requestTypes.AWARDS);

        if(app.responseTest != null) {
            System.out.println("success: " + app.recieveBody());
            
        }
    }
}
