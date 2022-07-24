package com.rick.jetpackpagingjava.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import com.rick.jetpackpagingjava.bean.RepoBean;
import com.rick.jetpackpagingjava.paging.RepoPagingSource;

public class HeheViewModel extends ViewModel {
    public LiveData<PagingData<RepoBean>> repoList = PagingLiveData.getLiveData(new Pager<>(new PagingConfig(/* pageSize = */ 5), RepoPagingSource::new));
}
