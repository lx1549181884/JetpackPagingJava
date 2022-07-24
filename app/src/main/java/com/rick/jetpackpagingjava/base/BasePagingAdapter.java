package com.rick.jetpackpagingjava.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.rick.jetpackpagingjava.common.CommonViewHolder;
import com.rick.jetpackpagingjava.util.BindingUtil;

import kotlinx.coroutines.CoroutineDispatcher;

public abstract class BasePagingAdapter<Bean, Binding extends ViewDataBinding> extends PagingDataAdapter<Bean, CommonViewHolder<Binding>> {

    public BasePagingAdapter(@NonNull DiffUtil.ItemCallback<Bean> diffCallback) {
        super(diffCallback);
    }

    public BasePagingAdapter(@NonNull DiffUtil.ItemCallback<Bean> diffCallback, @NonNull CoroutineDispatcher mainDispatcher) {
        super(diffCallback, mainDispatcher);
    }

    public BasePagingAdapter(@NonNull DiffUtil.ItemCallback<Bean> diffCallback, @NonNull CoroutineDispatcher mainDispatcher, @NonNull CoroutineDispatcher workerDispatcher) {
        super(diffCallback, mainDispatcher, workerDispatcher);
    }

    @NonNull
    @Override
    public CommonViewHolder<Binding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Binding binding = BindingUtil.createBinding(getClass(), BasePagingAdapter.class, 1, LayoutInflater.from(parent.getContext()), null);
        return new CommonViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder<Binding> holder, int position) {
        Bean bean = getItem(position);
        initItem(holder.binding, bean, position);
        holder.binding.executePendingBindings();
    }

    protected abstract void initItem(Binding binding, Bean bean, int position);
}
