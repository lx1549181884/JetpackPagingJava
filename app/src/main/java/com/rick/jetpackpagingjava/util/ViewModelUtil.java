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

    /**
     * 根据某个 ViewModelStoreOwner 声明的 ViewModel 类型，获取该 ViewModel 实例
     *
     * @param child        某个 ViewModelStoreOwner
     * @param parentClass  ViewModel 泛型所在的 ViewModelStoreOwner 父类 Class
     * @param genericIndex ViewModel 泛型在 ViewModelStoreOwner 父类 Class 的索引
     * @param <C>          子 ViewModelStoreOwner 类型
     * @param <P>          父 ViewModelStoreOwner 类型
     * @param <V>          子 ViewModelStoreOwner 声明的 ViewModel 类型
     * @return 子 ViewModelStoreOwner 声明的 ViewModel 实例
     */
    public static <C extends P, P extends ViewModelStoreOwner, V extends ViewModel> V getViewModel(@NonNull C child, @NonNull Class<P> parentClass, @IntRange(from = 0) int genericIndex) {
        return getViewModel(child, GenericUtil.getGenericClass(child.getClass(), parentClass, genericIndex));
    }

    /**
     * 根据 ViewModelStoreOwner 获取 ViewModel
     *
     * @param owner ViewModelStoreOwner
     * @param clazz ViewModel Class
     * @param <V>   要获取的 ViewModel 类型
     * @return 要获取的 ViewModel
     */
    public static <V extends ViewModel> V getViewModel(@NonNull ViewModelStoreOwner owner, @NonNull Class<V> clazz) {
        return new ViewModelProvider(owner).get(clazz);
    }
}
