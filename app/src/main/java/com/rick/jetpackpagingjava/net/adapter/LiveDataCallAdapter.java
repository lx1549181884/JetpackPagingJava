package com.rick.jetpackpagingjava.net.adapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<T> implements CallAdapter<T, LiveData<T>> {

    public interface Convert<T> {
        T onResponse(@NonNull Call<T> call, @NonNull Response<T> response);

        T onFailure(@NonNull Call<T> call, @NonNull Throwable t);
    }

    private final Type responseType;
    private final Convert<T> convert;

    public LiveDataCallAdapter(@NonNull Type responseType, @NonNull Convert<T> convert) {
        this.responseType = responseType;
        this.convert = convert;
    }

    @NonNull
    @Override
    public Type responseType() {
        return responseType;
    }

    @NonNull
    @Override
    public LiveData<T> adapt(@NonNull Call<T> call) {
        return new LiveData<T>() {
            final AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {//确保执行一次
                    call.enqueue(new Callback<T>() {
                        @Override
                        public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                            postValue(convert.onResponse(call, response));
                        }

                        @Override
                        public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                            postValue(convert.onFailure(call, t));
                        }
                    });
                }
            }
        };
    }
}
