package com.rick.jetpackpagingjava.net.adapter;

import androidx.annotation.NonNull;

import com.rick.jetpackpagingjava.bean.ResponseBean;

import retrofit2.Call;
import retrofit2.Response;

public class Convert implements LiveDataCallAdapter.Convert<ResponseBean<?>> {
    @Override
    public ResponseBean<?> onResponse(@NonNull Call<ResponseBean<?>> call, @NonNull Response<ResponseBean<?>> response) {
        try {
            if (response.isSuccessful()) {
                return response.body();
            } else {
                return new ResponseBean<>(response.code(), response.message(), null);
            }
        } catch (Exception e) {
            return new ResponseBean<>(-1, e.getMessage(), null);
        }
    }

    @Override
    public ResponseBean<?> onFailure(@NonNull Call<ResponseBean<?>> call, @NonNull Throwable t) {
        return new ResponseBean<>(-1, t.getMessage(), null);
    }
}
