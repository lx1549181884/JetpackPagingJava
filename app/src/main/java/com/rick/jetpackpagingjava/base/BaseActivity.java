package com.rick.jetpackpagingjava.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import com.rick.jetpackpagingjava.util.BindingUtil;
import com.rick.jetpackpagingjava.util.ViewModelUtil;

public abstract class BaseActivity<Binding extends ViewDataBinding, Vm extends ViewModel> extends AppCompatActivity {

    private Binding binding;
    private Vm vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BindingUtil.createBinding(this);
        setContentView(binding.getRoot());
        vm = ViewModelUtil.getViewModel(this);
        init(binding, vm);
    }

    protected abstract void init(Binding binding, Vm vm);
}
