package com.rick.jetpackpagingjava.util;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;

import com.blankj.utilcode.util.ReflectUtils;
import com.rick.jetpackpagingjava.base.BaseActivity;
import com.rick.jetpackpagingjava.base.BaseFragment;

/**
 * ViewDataBinding 工具
 */
public class BindingUtil {

    public static <F extends BaseFragment<?, ?>, B extends ViewDataBinding> B createBinding(@NonNull F fragment) {
        return BindingUtil.createBinding(fragment.getClass(), BaseFragment.class, 0, fragment.getLayoutInflater(), fragment.getViewLifecycleOwner());
    }

    public static <A extends BaseActivity<?, ?>, B extends ViewDataBinding> B createBinding(@NonNull A activity) {
        return BindingUtil.createBinding(activity.getClass(), BaseActivity.class, 0, activity.getLayoutInflater(), activity);
    }

    public static <B extends ViewDataBinding> B createBinding(@NonNull Class<?> childClass, @NonNull Class<?> parentClass, @IntRange(from = 0) int genericIndex, @NonNull LayoutInflater inflater, @NonNull LifecycleOwner owner) {
        Class<B> genericClass = GenericUtil.getGenericClass(childClass, parentClass, genericIndex);
        B binding = createBinding(genericClass, inflater, null, false);
        binding.setLifecycleOwner(owner);
        return binding;
    }

    public static <B extends ViewDataBinding> B createBinding(@NonNull Class<B> clazz, @NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, boolean attachToRoot) {
        return ReflectUtils.reflect(clazz).method("inflate", inflater, viewGroup, attachToRoot).get();
    }
}
