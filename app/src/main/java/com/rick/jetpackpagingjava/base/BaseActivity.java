package com.rick.jetpackpagingjava.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import com.rick.jetpackpagingjava.util.BindingUtil;
import com.rick.jetpackpagingjava.util.ViewModelUtil;

/**
 * BaseActivity
 * 会自动生成子类声明的 ViewDataBinding 和 ViewModel
 * 子类请在 {@link BaseActivity#init(ViewDataBinding, ViewModel)} 完成初始化工作
 */
public abstract class BaseActivity<Binding extends ViewDataBinding, Vm extends ViewModel> extends AppCompatActivity {

    protected Binding binding;
    protected Vm vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = createBinding();
        setContentView(binding.getRoot());
        vm = getViewModel();
        init(binding, vm);
    }

    /**
     * 创建 ViewDataBinding
     */
    @NonNull
    private Binding createBinding() {
        return BindingUtil.createBinding(getClass(), BaseActivity.class, 0, getLayoutInflater(), this, null, false);
    }

    /**
     * 创建 ViewModel
     */
    @NonNull
    private Vm getViewModel() {
        return ViewModelUtil.getViewModel(this, BaseActivity.class, 1);
    }

    /**
     * 初始化
     */
    protected abstract void init(Binding binding, Vm vm);
}
