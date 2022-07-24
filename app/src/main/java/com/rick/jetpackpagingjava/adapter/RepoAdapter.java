package com.rick.jetpackpagingjava.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.blankj.utilcode.util.GsonUtils;
import com.rick.jetpackpagingjava.base.BasePagingAdapter;
import com.rick.jetpackpagingjava.bean.RepoBean;
import com.rick.jetpackpagingjava.databinding.ItemRepoBinding;
import com.rick.jetpackpagingjava.util.LogUtil;

import java.util.Objects;

public class RepoAdapter extends BasePagingAdapter<RepoBean, ItemRepoBinding> {
    public RepoAdapter() {
        super(new DiffUtil.ItemCallback<RepoBean>() {
            @Override
            public boolean areItemsTheSame(@NonNull RepoBean oldItem, @NonNull RepoBean newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull RepoBean oldItem, @NonNull RepoBean newItem) {
                return Objects.equals(oldItem, newItem);
            }
        });
    }

    @Override
    protected void initItem(ItemRepoBinding binding, RepoBean bean, int position) {
        LogUtil.log("initItem " + GsonUtils.toJson(bean));
        binding.setRepoBean(bean);
    }
}
