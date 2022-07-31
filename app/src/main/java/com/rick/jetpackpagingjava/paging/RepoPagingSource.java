package com.rick.jetpackpagingjava.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.ListenableFuturePagingSource;
import androidx.paging.PagingState;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.rick.jetpackpagingjava.bean.RepoBean;
import com.rick.jetpackpagingjava.net.api.GithubService;
import com.rick.jetpackpagingjava.util.LogUtil;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.HttpException;

public class RepoPagingSource extends ListenableFuturePagingSource<Integer, RepoBean> {
    private final GithubService githubService = GithubService.create();
    private final Executor mBgExecutor = Executors.newSingleThreadExecutor();
    private final int pageSize = 5;

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, RepoBean> pagingState) {
        LogUtil.log("getRefreshKey");
        return null;
    }

    @NonNull
    @Override
    public ListenableFuture<LoadResult<Integer, RepoBean>> loadFuture(@NonNull LoadParams<Integer> params) {
        Integer key = params.getKey();
        int page = key == null ? 1 : key;
        ListenableFuture<LoadResult<Integer, RepoBean>> pageFuture =
                Futures.transform(githubService.search2(page, params.getLoadSize())
                        , response -> new LoadResult.Page<>(response.items,
                                page > 1 ? page - 1 : null,
                                response.items.isEmpty() ? null : (page + params.getLoadSize() / pageSize),
                                LoadResult.Page.COUNT_UNDEFINED,
                                LoadResult.Page.COUNT_UNDEFINED), mBgExecutor);

        ListenableFuture<LoadResult<Integer, RepoBean>> partialLoadResultFuture =
                Futures.catching(pageFuture, HttpException.class,
                        LoadResult.Error::new, mBgExecutor);

        return Futures.catching(partialLoadResultFuture,
                IOException.class, LoadResult.Error::new, mBgExecutor);
    }
}
