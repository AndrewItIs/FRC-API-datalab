package org.shrewsburyrobotics.datalab.datafetch;

import java.io.IOException;

import org.shrewsburyrobotics.datalab.datawrite.fileIndex;
import org.shrewsburyrobotics.datalab.datawrite.fileWriter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class datafetcher {
//create signleton for the httpClient
private final OkHttpClient mHttpClient = new OkHttpClient();
public String mBody;
public static String mStaticBody;
private int mTimeOut;
public String mUrl = "https://frc-api.firstinspires.org/v2.0/";
public String mParamURL = "?";
public fileIndex mFileIndex;
public requestTypes rtype;

public datafetcher() {

}

/**
 * for a general use
 */
public datafetcher dataFetch(int timeout, yearIndex season , requestTypes requestType) {
        mTimeOut = timeout; //no use currently
        //System.out.println("DataFetcher 1" + season);
        //season switch
        switch(season) {
            case POWER_UP:
                this.mUrl += "2018/";
                break;

            case DEEPSPACE:
                //System.out.println("deepspace " + this.mUrl);
                this.mUrl += "2019/";
                //System.out.println("deepspace 2 " + this.mUrl);           
                break;

            case INFINITE_RECHARGE:
                this.mUrl += "2020/";
                break;

            default:
            //defaults to current year (made in 2020)
                this.mUrl += "2020/";
        }

        switch(requestType){
            case MATCHES:
                this.mUrl += "matches/";
                break;

            case AWARDS:
                this.mUrl += "awards/";
                break;

            case EVENTS:
                this.mUrl += "events/";
                break;

            case TEAMS:
                this.mUrl += "teams/";
                break;

            case SCORES:
                this.mUrl += "scores/";
                break;

            default:
                //defaults to matches
                this.mUrl += "matches/";
            
            this.rtype = requestType;
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
        this.mTimeOut = timeout; //no use currently
        //System.out.println("DataFetcher 2" + season);
        //season switch
        switch(season) {
            case POWER_UP:
                this.mUrl += "2018/";
                break;

            case DEEPSPACE:
            //System.out.println("deepspace " + this.mUrl);
                this.mUrl += "2019/";
                //System.out.println("deepspace 2 " + this.mUrl);
                break;

            case INFINITE_RECHARGE:
                this.mUrl += "2020/";
                break;

            default:
            //defaults to current year (made in 2020)
                this.mUrl += "2020/";
        }

        switch(requestType){
            case MATCHES:
                this.mUrl += "matches/";
                break;

            case AWARDS:
                this.mUrl += "awards/";
                break;

            case EVENTS:
                this.mUrl += "events/";
                break;

            case TEAMS:
                this.mUrl += "teams/";
                break;

            case SCORES:
                this.mUrl += "scores/";
                break;

            default:
                //defaults to matches
                this.mUrl += "matches/";
        }

        this.mUrl += eventCode +"/";
        this.rtype = requestType;
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
        this.mTimeOut = timeout; //no use currently

        //season switch
        switch(season) {
            case POWER_UP:
                this.mUrl += "2018/";
                break;

            case DEEPSPACE:
                this.mUrl += "2019/";
                break;

            case INFINITE_RECHARGE:
                this.mUrl += "2020/";
                break;

            default:
            //defaults to current year (made in 2020)
                this.mUrl += "2020/";
        }

        switch(requestType){
            case MATCHES:
                this.mUrl += "matches/";
                break;

            case AWARDS:
                this.mUrl += "awards/";
                break;

            case EVENTS:
                this.mUrl += "events/";
                break;

            case TEAMS:
                this.mUrl += "teams/";
                break;

            case SCORES:
                this.mUrl += "scores/";
                break;

            default:
                //defaults to matches
                this.mUrl += "matches/";
        }

        this.mUrl += eventCode +"/";

        switch(level) {
            case QUALIFICATIONS:
                this.mUrl += "qual/";
                break;
            
            case FINALS:
                this.mUrl += "playoff/";
        }
        this.rtype = requestType;
        return this;
    }

    /**
     * add parameters
     * @param name name of the paramter (string)
     * @param value string value of the parameter
     */
    public datafetcher addParameter(String name, String value) {
        this.mParamURL = this.mParamURL + name + "=" + value + "&";
        return this;
    }

    public datafetcher removeParameters() {
        this.mParamURL = "?";
        return this;
    }


    public void sendGet() throws IOException {
        
        this.mUrl += this.mParamURL;

        Request request = new Request.Builder()
            .url(""+ this.mUrl)
            .get()
            .addHeader("Authorization", "Basic YW5kcmV3NDY3OjZGMkM0NzA1LUQyNjEtNDJGMi05OEIyLTc4QTBDNjZCRkI1NA==")
            .addHeader("Accept", "application/json")
            .build();
        
        try (Response mResponse = mHttpClient.newCall(request).execute()) {          
            if(!mResponse.isSuccessful()) throw new IOException("Unexpected error: " + mResponse);

            this.mBody = mResponse.body().string();
           // System.out.println("sendget 2" + this.mBody);
            this.mStaticBody = this.mBody;
            //System.out.println("sendget 2" + this.mStaticBody);
        } catch(IOException e) {
            e.printStackTrace();
        }
        //System.out.println("sendget 445" + this.mBody);
    }

    public datafetcher clearUrl() {
        this.mUrl =  "https://frc-api.firstinspires.org/v2.0/";
        this.mParamURL = "?";
        return this;
    }

    public String requestUrl() {
        return this.mUrl;
    }



    public String receiveBody() {
        //System.out.println("sendget body" + this.mBody);
        return this.mStaticBody;
    }
}