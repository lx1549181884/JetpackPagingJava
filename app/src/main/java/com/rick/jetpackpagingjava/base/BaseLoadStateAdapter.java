package com.rick.jetpackpagingjava.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;

import com.rick.jetpackpagingjava.common.CommonViewHolder;
import com.rick.jetpackpagingjava.util.BindingUtil;
import com.rick.jetpackpagingjava.util.LogUtil;

public abstract class BaseLoadStateAdapter<T extends ViewDataBinding> extends LoadStateAdapter<CommonViewHolder<T>> {

    private final Runnable retry;

    public BaseLoadStateAdapter(Runnable retry) {
        this.retry = retry;
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder<T> holder, @NonNull LoadState loadState) {
        LogUtil.log("BaseLoadStateAdapter onBindViewHolder " + loadState);
        initItem(holder.binding, loadState);
        holder.binding.getRoot().setOnClickListener(view -> {
            if (loadState instanceof LoadState.Error && retry != null) retry.run();
        });
        holder.binding.executePendingBindings();
    }

    @NonNull
    @Override
    public CommonViewHolder<T> onCreateViewHolder(@NonNull ViewGroup viewGroup, @NonNull LoadState loadState) {
        LogUtil.log("BaseLoadStateAdapter onCreateViewHolder " + loadState);
        T binding = BindingUtil.createBinding(getClass(), BaseLoadStateAdapter.class, 0, LayoutInflater.from(viewGroup.getContext()), null);
        return new CommonViewHolder<>(binding);
    }

    protected abstract void initItem(T binding, LoadState loadState);
}
