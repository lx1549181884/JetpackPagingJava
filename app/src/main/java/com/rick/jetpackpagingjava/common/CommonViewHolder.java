package com.rick.jetpackpagingjava.common;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class CommonViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public final T binding;

    public CommonViewHolder(@NonNull T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
