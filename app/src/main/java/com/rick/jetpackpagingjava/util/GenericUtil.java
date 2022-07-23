package com.rick.jetpackpagingjava.util;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型工具
 */
public class GenericUtil {

    public static <G> Class<G> getGenericClass(@NonNull Class<?> childClass, @NonNull Class<?> parentClass, @IntRange(from = 0) int genericIndex) {
        // 先找父类
        Class<?> superclass = childClass.getSuperclass();
        if (parentClass.equals(superclass)) {
            return getGenericClass(childClass, genericIndex);
        } else {
            // 若没找到，再找接口
            Class<?>[] interfacesClass = childClass.getInterfaces();
            for (Class<?> interfaceClass : interfacesClass) {
                if (parentClass.equals(interfaceClass)) {
                    return getGenericClass(childClass, genericIndex);
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

    private static <G> Class<G> getGenericClass(@NonNull Class<?> clazz, @IntRange(from = 0) int genericIndex) {
        Type genericSuperclass = clazz.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class<G> genericClass = (Class<G>) actualTypeArguments[genericIndex];
        return genericClass;
    }
}
