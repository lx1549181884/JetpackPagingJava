package com.rick.jetpackpagingjava.adapter;

import androidx.paging.LoadState;

import com.rick.jetpackpagingjava.base.BaseLoadStateAdapter;
import com.rick.jetpackpagingjava.databinding.LoadStateBinding;
import com.rick.jetpackpagingjava.util.LogUtil;

public class LoadStateAdapter extends BaseLoadStateAdapter<LoadStateBinding> {

    public LoadStateAdapter(Runnable retry) {
        super(retry);
    }

    @Override
    protected void initItem(LoadStateBinding binding, LoadState loadState) {
        binding.setLoadState(loadState);
    }
}
