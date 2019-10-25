package org.shrewsburyrobotics.datalab;

import org.shrewsburyrobotics.datalab.datafetch.*;
import java.io.IOException;

public class App {
    private datafetcher mDataFetcher;

    private void runAwards() {
        mDataFetcher = new datafetcher(300, yearIndex.DEEPSPACE, requestTypes.AWARDS, "MABOS");
        mDataFetcher.addParameter("teamNumber", 467);
        
        try {
        mDataFetcher.sendGet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) {
        App app = new App();
        app.runAwards();
    }
}
