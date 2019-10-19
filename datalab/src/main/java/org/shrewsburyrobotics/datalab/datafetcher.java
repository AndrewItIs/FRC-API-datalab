package org.shrewsburyrobotics.datalab;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class datafetcher {
    private final OkHttpClient httpClient = new OkHttpClient();

    private datafetcher() {

    }

    public void sendGet() throws Exception {
        Request request = new Request.Builder()
            .url("https://frc-api.firstinspires.org/v2.0/2019/awards/MABOS/")
            .get()
            .addHeader("Authorization", "Basic YW5kcmV3NDY3OjZGMkM0NzA1LUQyNjEtNDJGMi05OEIyLTc4QTBDNjZCRkI1NA==")
            .addHeader("Accept", "application/json")
            .build();
        
        try (Response response = httpClient.newCall(request).execute()) {


        }
        }
}