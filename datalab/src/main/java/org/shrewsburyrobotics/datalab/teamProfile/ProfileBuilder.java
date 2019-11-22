package org.shrewsburyrobotics.datalab.teamProfile;

import org.shrewsburyrobotics.datalab.datafetch.*;
import org.shrewsburyrobotics.datalab.datawrite.*;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileBuilder {
    //will not have a singleton, each instance will represent a new team
    private int mTeamNumber;
    private yearIndex mSeason;
    private String mEventString;
    public ArrayList<String> mEventArray = new ArrayList<String>(0);
    public ArrayList<String> mMatchArray = new ArrayList<String>(0);

    private datafetcher mEventDatafetcher;

    public ProfileBuilder(int teamNumber, yearIndex season) {

        
        //assign team number
        mTeamNumber = teamNumber;
        mSeason = season;

        //find all events
        try {
            mEventDatafetcher = new datafetcher()
                .dataFetch(300, season, requestTypes.EVENTS)
                .addParameter("districtCode", Double.toString(teamNumber));
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

    public ProfileBuilder addMatches() {
        datafetcher mMatchDatafetcher = new datafetcher();
        for(int i = 0; i <= mEventArray.size(); i++) {
            try {            
                mMatchDatafetcher.dataFetch(300, mSeason, requestTypes.MATCHES, mEventArray.get(i))
                .addParameter("teamNumber", Double.toHexString(mTeamNumber))
                .sendGet(); 
            } catch (IOException e) {
                e.printStackTrace();
            }

            mMatchArray.add(mMatchDatafetcher.receiveBody());
        }

        return this;
    }
}