package org.shrewsburyrobotics.datalab;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class datafetcher {
    //create signleton for the httpClient
    private final OkHttpClient mHttpClient = new OkHttpClient();
    public String mBody;
    private int mTimeOut;


    public datafetcher(int timeout) {
        mTimeOut = timeout;

    }

    public void sendGet() throws Exception {
        Request request = new Request.Builder()
            .url("https://frc-api.firstinspires.org/v2.0/2019/awards/MABOS/")
            .get()
            .addHeader("Authorization", "Basic YW5kcmV3NDY3OjZGMkM0NzA1LUQyNjEtNDJGMi05OEIyLTc4QTBDNjZCRkI1NA==")
            .addHeader("Accept", "application/json")
            .build();
        
        try (Response mResponse = mHttpClient.newCall(request).execute()) {          
            if(!mResponse.isSuccessful()) throw new IOException("Unexpected error: " + mResponse);

            mBody = mResponse.body().string();
        }
    }
}