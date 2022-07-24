package com.rick.jetpackpagingjava.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
        binding = createBinding();
        setContentView(binding.getRoot());
        vm = getViewModel();
        init(binding, vm);
    }

    @NonNull
    private Binding createBinding() {
        return BindingUtil.createBinding(getClass(), BaseActivity.class, 0, getLayoutInflater(), this);
    }

    @NonNull
    private Vm getViewModel() {
        return ViewModelUtil.getViewModel(this, BaseActivity.class, 1);
    }

    protected abstract void init(Binding binding, Vm vm);
}
