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
    private String mDistrictCode, mDistrictCodeBody;
    private datafetcher mEventDatafetcher;

    public ProfileBuilder(int teamNumber, yearIndex season) {

        
        //assign team number
        this.mTeamNumber = teamNumber;
        this.mSeason = season;

        //find all events moved to event list table
        // try {
        //     mEventDatafetcher = new datafetcher()
        //         .dataFetch(300, season, requestTypes.EVENTS)
        //         .addParameter("districtCode", Double.toString(teamNumber));
        //     mEventDatafetcher.sendGet();
        //     mEventString = mEventDatafetcher.receiveBody();
        //     frcJsonParser mEventfWriter = new frcJsonParser(mEventString, requestTypes.EVENTS);
        //     for(int i = 0; i < mEventfWriter.docs.length(); i++) {
        //         mEventArray.add(mEventfWriter.docs.getJSONObject(i).toString());
        //     }
        // }  catch(IOException e) {
        //     e.printStackTrace();
        // }

    }

    public ProfileBuilder addDistrictCode() {
        datafetcher mDatafetcher = new datafetcher();
            try {            
                mDatafetcher.dataFetch(300, mSeason, requestTypes.EVENTS)
                .addParameter("teamNumber", Double.toString(mTeamNumber))
                .sendGet(); 
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.mDistrictCodeBody = mDatafetcher.receiveBody();
            frcJsonParser mDistrictWriter = new frcJsonParser(this.mDistrictCodeBody, requestTypes.EVENTS);
            this.mDistrictCode = mDistrictWriter.docs.getJSONObject(4).toString();

        return this;
    }
}