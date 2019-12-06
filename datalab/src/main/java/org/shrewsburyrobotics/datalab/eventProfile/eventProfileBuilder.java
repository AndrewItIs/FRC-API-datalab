package org.shrewsburyrobotics.datalab.eventProfile;

import org.shrewsburyrobotics.datalab.datafetch.*;
import org.shrewsburyrobotics.datalab.datawrite.*;

import java.io.IOException;
import java.util.ArrayList;

public class eventProfileBuilder {
    //will not have a singleton, each instance will represent a new team
    private int mTeamNumber;
    private yearIndex mSeason;
    private String mEventString;
    private String mDistrictCodeBody, mDistrictCode;
    private datafetcher mEventDatafetcher;

    public eventProfileBuilder(int teamNumber, yearIndex season, String mDistrictCode) {

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

    public eventProfileBuilder addDistrictCode() {
        datafetcher mDatafetcher = new datafetcher();
            try {            
                mDatafetcher.dataFetch(300, mSeason, requestTypes.EVENTS)
                .addParameter("districtCode", this.mDistrictCode)
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