package com.rick.jetpackpagingjava.adapter;

import androidx.paging.LoadState;

import com.rick.jetpackpagingjava.base.BaseLoadStateAdapter;
import com.rick.jetpackpagingjava.databinding.LoadStateBinding;

public class LoadStateAdapter extends BaseLoadStateAdapter<LoadStateBinding> {

    public LoadStateAdapter(Runnable retry) {
        super(retry);
    }

    @Override
    protected void initItem(LoadStateBinding binding, LoadState loadState) {
        if (loadState instanceof LoadState.Error) {
            binding.tv.setText("加载错误，点击重试");
        } else if (loadState instanceof LoadState.Loading) {
            binding.tv.setText("加载中...");
        } else {
            binding.tv.setText("");
        }
    }
}
