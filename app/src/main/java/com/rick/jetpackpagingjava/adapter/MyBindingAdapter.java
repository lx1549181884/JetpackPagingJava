package com.rick.jetpackpagingjava.adapter;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.paging.LoadState;

public class MyBindingAdapter {
    @BindingAdapter("loadState")
    public static void loadState(TextView textView, LoadState loadState) {
        if (loadState instanceof LoadState.Loading) {
            textView.setText("正在加载...");
        } else if (loadState instanceof LoadState.Error) {
            textView.setText("加载失败");
        } else {
            textView.setText("");
        }
    }
}
