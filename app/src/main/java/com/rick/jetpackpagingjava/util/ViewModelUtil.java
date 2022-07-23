package com.rick.jetpackpagingjava.util;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.rick.jetpackpagingjava.base.BaseActivity;
import com.rick.jetpackpagingjava.base.BaseFragment;

/**
 * ViewModel 工具
 */
public class ViewModelUtil {

    public static <F extends BaseFragment<?, ?>, V extends ViewModel> V getViewModel(@NonNull F fragment) {
        return ViewModelUtil.getViewModel(fragment, BaseFragment.class, 1);
    }

    public static <A extends BaseActivity<?, ?>, V extends ViewModel> V getViewModel(@NonNull A activity) {
        return ViewModelUtil.getViewModel(activity, BaseActivity.class, 1);
    }

    public static <C extends P, P extends ViewModelStoreOwner, T extends ViewModel> T getViewModel(@NonNull C child, @NonNull Class<P> parentClass, @IntRange(from = 0) int genericIndex) {
        return getViewModel(child, GenericUtil.getGenericClass(child.getClass(), parentClass, genericIndex));
    }

    public static <V extends ViewModel> V getViewModel(@NonNull ViewModelStoreOwner owner, @NonNull Class<V> clazz) {
        return new ViewModelProvider(owner).get(clazz);
    }
}