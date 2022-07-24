package com.rick.jetpackpagingjava.net.api;

import androidx.lifecycle.LiveData;

import com.google.common.util.concurrent.ListenableFuture;
import com.rick.jetpackpagingjava.bean.RepoBean;
import com.rick.jetpackpagingjava.bean.ResponseBean;
import com.rick.jetpackpagingjava.net.adapter.Convert;
import com.rick.jetpackpagingjava.net.adapter.LiveDataCallAdapterFactory;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.guava.GuavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubService {
    static GithubService create() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create(new Convert()))
                .addCallAdapterFactory(GuavaCallAdapterFactory.create())
                .build()
                .create(GithubService.class);
    }

    @GET("search/repositories?sort=stars&q=Android")
    LiveData<ResponseBean<List<RepoBean>>> search(@Query("page") int page, @Query("per_page") int per_page);

    @GET("search/repositories?sort=stars&q=Android")
    ListenableFuture<ResponseBean<List<RepoBean>>> search2(@Query("page") int page, @Query("per_page") int per_page);
}
