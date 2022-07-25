package com.rick.jetpackpagingjava.net.adapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * Retrofit 的 CallAdapterFactory，将 Service 方法返回的 Call 转为 LiveData
 */
public class LiveDataCallAdapterFactory<T> extends CallAdapter.Factory {

    public static <T> LiveDataCallAdapterFactory<T> create(@NonNull LiveDataCallAdapter.Convert<T> convert) {
        return new LiveDataCallAdapterFactory<>(convert);
    }

    @NonNull
    private final LiveDataCallAdapter.Convert<T> convert;

    private LiveDataCallAdapterFactory(@NonNull LiveDataCallAdapter.Convert<T> convert) {
        this.convert = convert;
    }

    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
        if (getRawType(returnType) != LiveData.class) return null;
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        if (!(observableType instanceof ParameterizedType)) {
            throw new IllegalArgumentException("resource must be parameterized");
        }
        return new LiveDataCallAdapter<>(observableType, convert);
    }
}
