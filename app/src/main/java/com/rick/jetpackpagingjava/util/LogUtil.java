package com.rick.jetpackpagingjava.util;

import android.util.Log;

public class LogUtil {
    public static void log(Object... objects) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < objects.length; i++) {
            builder.append(objects[i]);
            if (i < objects.length - 1) {
                builder.append(",");
            }
        }
        Log.d("LogUtil", builder.toString());
    }
}
