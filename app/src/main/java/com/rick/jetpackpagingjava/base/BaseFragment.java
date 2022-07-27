package com.rick.jetpackpagingjava.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.rick.jetpackpagingjava.util.BindingUtil;
import com.rick.jetpackpagingjava.util.ViewModelUtil;

/**
 * BaseFragment
 * 会自动生成子类声明的 ViewDataBinding 和 ViewModel
 * 子类请在 {@link BaseFragment#init(ViewDataBinding, ViewModel)} 完成初始化工作
 */
public abstract class BaseFragment<Binding extends ViewDataBinding, Vm extends ViewModel> extends Fragment {

    protected Binding binding;
    protected Vm viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = createBinding();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = getViewModel();
        init(binding, viewModel);
    }

    /**
     * 创建 ViewDataBinding
     */
    @NonNull
    private Binding createBinding() {
        return BindingUtil.createBinding(getClass(), BaseFragment.class, 0, getLayoutInflater(), getViewLifecycleOwner(), null, false);
    }

    /**
     * 创建 ViewModel
     */
    @NonNull
    private Vm getViewModel() {
        return ViewModelUtil.getViewModel(this, BaseFragment.class, 1);
    }

    /**
     * 初始化
     */
    protected abstract void init(Binding binding, Vm viewModel);
}
