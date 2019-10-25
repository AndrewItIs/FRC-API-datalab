package org.shrewsburyrobotics.datalab;

import org.shrewsburyrobotics.datalab.datafetch.*;

import okhttp3.ResponseBody;

import java.io.IOException;

public class App {
    private datafetcher mDataFetcher;
    private ResponseBody responseTest;
    
    private void runAwards() {
        mDataFetcher = new datafetcher(300, yearIndex.DEEPSPACE, requestTypes.AWARDS, "MABOS");
        mDataFetcher.addParameter("teamNumber", 467);
        
        try {
        mDataFetcher.sendGet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void recieveBody() {
       responseTest = mDataFetcher.receiveBody();
    }


    public static void main( String[] args ) {
        App app = new App();
        app.runAwards();
        app.recieveBody();

        if(app.responseTest != null) {
 //           try {
            System.out.println(app.responseTest.toString());
 //           } catch (IOException e) {
 //               e.printStackTrace();
           // }
        }

    }
}
