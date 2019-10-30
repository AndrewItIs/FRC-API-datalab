package org.shrewsburyrobotics.datalab.teamProfile;

import org.shrewsburyrobotics.datalab.datafetch.*;
import org.shrewsburyrobotics.datalab.datawrite.*;

import java.io.IOException;
import java.util.ArrayList;

public class teamProfileBuilder {
    //will not have a singleton, each instance will represent a new team
    private int mTeamNumber;
    private yearIndex mSeason;
    private String mEventString;
    public ArrayList<String> mEventArray = new ArrayList<String>(0);
    public ArrayList<String> mMatchArray;

    private datafetcher mEventDatafetcher;

    public teamProfileBuilder(int teamNumber, yearIndex season) {

        
        //assign team number
        mTeamNumber = teamNumber;
        mSeason = season;

        //find all events
        try {
            mEventDatafetcher = new datafetcher()
                .dataFetch(300, season, requestTypes.EVENTS)
                .addParameter("teamNumber", teamNumber);
            mEventDatafetcher.sendGet();
            mEventString = mEventDatafetcher.receiveBody();
            frcJsonParser mEventfWriter = new frcJsonParser(mEventString, requestTypes.EVENTS);
            for(int i = 0; i < mEventfWriter.docs.length(); i++) {
                mEventArray.add(mEventfWriter.docs.getJSONObject(i).toString());
            }
        }  catch(IOException e) {
            e.printStackTrace();
        }

    }

    public teamProfileBuilder addMatches() {
        datafetcher mMatchDatafetcher = new datafetcher();
//        mMatchDatafetcher.dataFetch(300, mSeason, requestTypes.MATCHES, "MABOS").addParameter("", value);
        return this;
    }
}