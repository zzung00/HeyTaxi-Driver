package com.example.heytaxi_driver.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeyTaxiAPI {
    private static HeyTaxiAPI instance = null;
    private static HeyTaxiService service;
    private static final String baseUrl = "http://220.87.153.233/";

    @RequiresApi(api = Build.VERSION_CODES.O)
    private HeyTaxiAPI() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context)
                        -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context)
                        -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (json, typeOfT, context)
                        -> LocalTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("HH:mm:ss")))
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (localDateTime, typeOfT, context)
                        -> new JsonPrimitive(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime)))
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (localDate, typeOfT, context)
                        -> new JsonPrimitive(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate)))
                .registerTypeAdapter(LocalTime.class, (JsonSerializer<LocalTime>) (localTime, typeOfT, context)
                        -> new JsonPrimitive(DateTimeFormatter.ofPattern("HH:mm:ss").format(localTime)))
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).build();
        service = retrofit.create(HeyTaxiService.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static HeyTaxiAPI getInstance() {
        if (instance == null) {
            instance = new HeyTaxiAPI();
        }
        return instance;
    }

    public HeyTaxiService getService() {
        return service;
    }
}

