package org.shrewsburyrobotics.datalab.datafetch;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class datafetcher {
//create signleton for the httpClient
private final OkHttpClient mHttpClient = new OkHttpClient();
public String mBody;
private int mTimeOut;
private String mUrl = "https://frc-api.firstinspires.org/v2.0/";
private String mParamURL = "?";
public requestTypes rtype;

public datafetcher() {

}

/**
 * for a general use
 */
public datafetcher dataFetch(int timeout, yearIndex season , requestTypes requestType) {
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
        return this;
    }

    /**
     *specficially meant for event listings
     * @param timeout unused
     * @param season
     * @param requestType
     * @param eventCode
     */
    public datafetcher dataFetch(int timeout, yearIndex season, requestTypes requestType, String eventCode) {
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
        return this;
    }

    /**
     * meant for specific scores
     * @param timeout
     * @param season
     * @param requestType
     * @param eventCode
     * @param level
     */
    public datafetcher dataFetch(int timeout, yearIndex season, requestTypes requestType, String eventCode, tournementLevel level) {
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

        mUrl += eventCode +"/";

        switch(level) {
            case QUALIFICATIONS:
                mUrl += "qual/";
                break;
            
            case FINALS:
                mUrl += "playoff/";
        }
        rtype = requestType;
        return this;
    }

    /**
     * add parameters
     * @param name name of the paramter (string)
     * @param value string value of the parameter
     */
    public datafetcher addParameter(String name, String value) {
        mParamURL = mParamURL + name + "=" + value + "&";
        return this;
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

            mBody = mResponse.body().string();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public datafetcher clearUrl() { 
        mUrl =  "https://frc-api.firstinspires.org/v2.0/";
        return this;
    }


    public String receiveBody(){
        return mBody;
    }
}