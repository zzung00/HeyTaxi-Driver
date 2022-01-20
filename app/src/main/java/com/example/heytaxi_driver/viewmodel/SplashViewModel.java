package com.example.heytaxi_driver.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.heytaxi_driver.service.HeyTaxiAPI;

import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashViewModel extends ViewModel {
    private MutableLiveData<Boolean> isConnected = new MutableLiveData<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LiveData<Boolean> isConnected() {
        Call<LocalDateTime> call = HeyTaxiAPI.getInstance().getService().serverConnect();

        call.enqueue(new Callback<LocalDateTime>() {
            @Override
            public void onResponse(Call<LocalDateTime> call, Response<LocalDateTime> response) {
                if (response.isSuccessful()) {
                    isConnected.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<LocalDateTime> call, Throwable t) {
                isConnected.setValue(false);
            }
        });
        return isConnected;
    }
}
