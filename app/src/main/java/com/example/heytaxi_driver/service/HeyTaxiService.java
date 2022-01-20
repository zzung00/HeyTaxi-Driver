package com.example.heytaxi_driver.service;

import com.example.heytaxi_driver.model.VerifyDTO;

import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HeyTaxiService {
    @GET("/")
    Call<LocalDateTime> serverConnect();

    @POST("/api/verify/request")
    Call<VerifyDTO.Response> verifyReq(@Body VerifyDTO.Request request);

    @POST("/api/verify/verify")
    Call<VerifyDTO.Response> verify(@Body VerifyDTO.Request request);
}
