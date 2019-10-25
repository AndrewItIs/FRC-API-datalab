package org.shrewsburyrobotics.datalab.datafetch;

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
private String mParamURL = "?";
public requestTypes rtype;

//contructor for general
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
            
            rtype = requestType;


        }
    }

    //for event listings
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
        rtype = requestType;
    }

    //for detailed scores
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
        rtype = requestType;

    }

    //adding parameters
    public void addParameter(String name, String value) {
        mParamURL = mParamURL + name + "=" + value + "&";

    }

    public void addParameter(String name, double value) {
        mParamURL = mParamURL + name + "=" + Double.toString(value) + "&";
    }

    public void sendGet() throws IOException {
        
        mUrl += mParamURL;

        Request request = new Request.Builder()
            .url(""+ mUrl)
            .get()
            .addHeader("Authorization", "Basic YW5kcmV3NDY3OjZGMkM0NzA1LUQyNjEtNDJGMi05OEIyLTc4QTBDNjZCRkI1NA==")
            .addHeader("Accept", "application/json")
            .build();
        
        try (Response mResponse = mHttpClient.newCall(request).execute()) {          
            if(!mResponse.isSuccessful()) throw new IOException("Unexpected error: " + mResponse);

            mBody = mResponse.body();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ResponseBody receiveBody(){
        return mBody;
    }
}