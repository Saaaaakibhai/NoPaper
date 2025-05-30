package com.example.nopaper.client;

import android.content.Context;
import android.content.SharedPreferences;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ApiClient {
    private static OkHttpClient client;

    public static OkHttpClient getClient(Context context) {
        if (client == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
            String token = sharedPreferences.getString("jwt_token", null);

            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if (token != null) {
                builder.addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Authorization", "Bearer " + token)
                            .build();
                    return chain.proceed(request);
                });
            }
            client = builder.build();
        }
        return client;
    }
}
