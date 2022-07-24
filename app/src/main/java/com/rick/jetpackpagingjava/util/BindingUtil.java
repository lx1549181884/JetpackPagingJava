package com.rick.jetpackpagingjava.util;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;

import com.blankj.utilcode.util.ReflectUtils;

/**
 * ViewDataBinding 工具
 */
public class BindingUtil {

    public static <B extends ViewDataBinding> B createBinding(@NonNull Class<?> childClass, @NonNull Class<?> parentClass, @IntRange(from = 0) int genericIndex, @NonNull LayoutInflater inflater, @Nullable LifecycleOwner owner) {
        Class<B> genericClass = GenericUtil.getGenericClass(childClass, parentClass, genericIndex);
        B binding = createBinding(genericClass, inflater, null, false);
        if (owner != null) {
            binding.setLifecycleOwner(owner);
        }
        return binding;
    }

    public static <B extends ViewDataBinding> B createBinding(@NonNull Class<B> clazz, @NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, boolean attachToRoot) {
        return ReflectUtils.reflect(clazz).method("inflate", inflater, viewGroup, attachToRoot).get();
    }
}
