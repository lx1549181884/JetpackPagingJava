package com.rick.jetpackpagingjava.util;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * 泛型工具
 */
public class GenericUtil {

    /**
     * 获取某个 Class 声明的类型的 Class
     *
     * @param childClass   某个 Class
     * @param parentClass  泛型所在的父 Class
     * @param genericIndex 泛型在父 Class 的索引
     * @param <G>          声明的类型
     * @return 声明的类型的 Class
     */
    public static <G> Class<G> getGenericClass(@NonNull Class<?> childClass, @NonNull Class<?> parentClass, @IntRange(from = 0) int genericIndex) {
        // 先找父类
        Class<?> superclass = childClass.getSuperclass();
        if (parentClass.equals(superclass)) {
            return getGenericClass((ParameterizedType) Objects.requireNonNull(childClass.getGenericSuperclass()), genericIndex);
        } else {
            // 若没找到，再找接口
            Class<?>[] interfacesClass = childClass.getInterfaces();
            for (int i = 0; i < interfacesClass.length; i++) {
                if (parentClass.equals(interfacesClass[i])) {
                    return getGenericClass((ParameterizedType) childClass.getGenericInterfaces()[i], genericIndex);
                }
            }
            // 若还是没找到
            if (superclass == null) { // 已经找到头了
                throw new RuntimeException("couldn't find " + parentClass);
            } else { // 继续往父类查找
                return getGenericClass(superclass, parentClass, genericIndex);
            }
        }
    }

    /**
     * 从 ParameterizedType 中获取声明的类型的 Class
     *
     * @param parameterizedType ParameterizedType 中包含了声明的类型的信息
     * @param genericIndex      声明的类型的索引
     * @param <G>               声明的类型
     * @return 声明的类型的 Class
     */
    private static <G> Class<G> getGenericClass(@NonNull ParameterizedType parameterizedType, @IntRange(from = 0) int genericIndex) {
        // 获取声明的类型数组
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        // 强转指定索引的 Type 为声明的类型的 Class
        Class<G> genericClass = (Class<G>) actualTypeArguments[genericIndex];
        return genericClass;
    }
}
