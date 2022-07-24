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

    /**
     * 根据某个 Class 声明的 ViewDataBinding 类型，创建该 ViewDataBinding 实例
     *
     * @param childClass   某个 Class
     * @param parentClass  ViewDataBinding 泛型所在的父 Class（可以是 interface 的 Class）
     * @param genericIndex ViewDataBinding 泛型在父 Class 的索引
     * @param inflater     布局填充器
     * @param owner        绑定的生命周期
     * @param <B>          声明的 ViewDataBinding
     * @return 声明的 ViewDataBinding 实例
     */
    public static <B extends ViewDataBinding> B createBinding(@NonNull Class<?> childClass, @NonNull Class<?> parentClass, @IntRange(from = 0) int genericIndex, @NonNull LayoutInflater inflater, @Nullable LifecycleOwner owner) {
        // 获取 ViewDataBinding Class
        Class<B> genericClass = GenericUtil.getGenericClass(childClass, parentClass, genericIndex);
        // 创建 ViewDataBinding
        B binding = createBinding(genericClass, inflater, null, false);
        // 绑定生命周期
        if (owner != null) {
            binding.setLifecycleOwner(owner);
        }
        return binding;
    }

    /**
     * 根据 ViewDataBinding Class 创建 ViewDataBinding 实例
     *
     * @param clazz        ViewDataBinding Class
     * @param inflater     布局填充器
     * @param viewGroup    父控件
     * @param attachToRoot 是否挂载在父控件
     * @param <B>          ViewDataBinding 类型
     * @return ViewDataBinding 实例
     */
    public static <B extends ViewDataBinding> B createBinding(@NonNull Class<B> clazz, @NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, boolean attachToRoot) {
        // 通过反射调用 XxxBinding.inflate(LayoutInflater, ViewGroup, boolean) 生成 XxxBinding
        return ReflectUtils.reflect(clazz).method("inflate", inflater, viewGroup, attachToRoot).get();
    }
}
