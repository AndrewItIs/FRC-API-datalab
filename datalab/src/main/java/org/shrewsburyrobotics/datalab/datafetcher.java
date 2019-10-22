package org.shrewsburyrobotics.datalab;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class datafetcher {
//create signleton for the httpClient
private final OkHttpClient mHttpClient = new OkHttpClient();
public ResponseBody mBody;
private int mTimeOut;
private String mUrl = "https://frc-api.firstinspires.org/v2.0/";

public datafetcher(int timeout, yearIndex season , requestTypes requestType) {
    mTimeOut = timeout; //no use currently

    //season switch
    switch(season) {
        case POWER_UP:
            mUrl += "2018/";
            break;

        case DEEPSPACE:
            mUrl += "2019/";
            break;

        case INFINITE_RECHARGE:
            mUrl += "2020/";
            break;

        default:
        //defaults to current year (made in 2020)
            mUrl += "2020/";
    }

    switch(requestType){
        case MATCHES:
            mUrl += "matches/";
            break;

        case AWARDS:
            mUrl += "awards/";
            break;

        case EVENTS:
            mUrl += "events/";
            break;

        case TEAMS:
            mUrl += "teams/";
            break;

        case SCORES:
            mUrl += "scores/";
            break;

        default:
            //defaults to matches
            mUrl += "matches/";
        


    }
}

public datafetcher(int timeout, yearIndex season, requestTypes requestType, String eventCode) {
    mTimeOut = timeout; //no use currently

    //season switch
    switch(season) {
        case POWER_UP:
            mUrl += "2018/";
            break;

        case DEEPSPACE:
            mUrl += "2019/";
            break;

        case INFINITE_RECHARGE:
            mUrl += "2020/";
            break;

        default:
        //defaults to current year (made in 2020)
            mUrl += "2020/";
    }

    switch(requestType){
        case MATCHES:
            mUrl += "matches/";
            break;

        case AWARDS:
            mUrl += "awards/";
            break;

        case EVENTS:
            mUrl += "events/";
            break;

        case TEAMS:
            mUrl += "teams/";
            break;

        case SCORES:
            mUrl += "scores/";
            break;

        default:
            //defaults to matches
            mUrl += "matches/";
    }

    mUrl += eventCode;
}

public datafetcher(int timeout, yearIndex season, requestTypes requestType, String eventCode, tournementLevel level) {
    mTimeOut = timeout; //no use currently

    //season switch
    switch(season) {
        case POWER_UP:
            mUrl += "2018/";
            break;

        case DEEPSPACE:
            mUrl += "2019/";
            break;

        case INFINITE_RECHARGE:
            mUrl += "2020/";
            break;

        default:
        //defaults to current year (made in 2020)
            mUrl += "2020/";
    }

    switch(requestType){
        case MATCHES:
            mUrl += "matches/";
            break;

        case AWARDS:
            mUrl += "awards/";
            break;

        case EVENTS:
            mUrl += "events/";
            break;

        case TEAMS:
            mUrl += "teams/";
            break;

        case SCORES:
            mUrl += "scores/";
            break;

        default:
            //defaults to matches
            mUrl += "matches/";
    }

    mUrl += eventCode;

    switch(level) {
        case QUALIFICATIONS:
            mUrl += "qual/";
            break;
        
        case FINALS:
            mUrl += "playoff/";
    }

}



public void sendGet() throws IOException {

    Request request = new Request.Builder()
        .url("https://frc-api.firstinspires.org/v2.0/2019/awards/MABOS/")
        .get()
        .addHeader("Authorization", "Basic YW5kcmV3NDY3OjZGMkM0NzA1LUQyNjEtNDJGMi05OEIyLTc4QTBDNjZCRkI1NA==")
        .addHeader("Accept", "application/json")
        .build();
    
    try (Response mResponse = mHttpClient.newCall(request).execute()) {          
        if(!mResponse.isSuccessful()) throw new IOException("Unexpected error: " + mResponse);

        mBody = mResponse.body();
    }
}
}