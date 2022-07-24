package com.rick.jetpackpagingjava.util;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

/**
 * ViewModel 工具
 */
public class ViewModelUtil {

    public static <C extends P, P extends ViewModelStoreOwner, T extends ViewModel> T getViewModel(@NonNull C child, @NonNull Class<P> parentClass, @IntRange(from = 0) int genericIndex) {
        return getViewModel(child, GenericUtil.getGenericClass(child.getClass(), parentClass, genericIndex));
    }

    public static <V extends ViewModel> V getViewModel(@NonNull ViewModelStoreOwner owner, @NonNull Class<V> clazz) {
        return new ViewModelProvider(owner).get(clazz);
    }
}
