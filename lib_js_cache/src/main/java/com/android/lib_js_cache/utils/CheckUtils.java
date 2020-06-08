package com.android.lib_js_cache.utils;

import org.json.JSONObject;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Description : 条件判断帮助类
 */
public class CheckUtils {

    private CheckUtils(){
    }

    /**
     * 判断对象是否为Null
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }


    /**
     * 判断对象不为Null
     *
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 若判断对象为null输出""
     *
     * @param obj
     * @return
     */
    public static String ifNull(Object obj) {
        return CheckUtils.ifNull(obj, "");
    }

    /**
     * 若判断对象为null输出默认值
     *
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String ifNull(Object obj, String defaultValue) {
        if (isNull(obj)) {
            return defaultValue;
        } else {
            return obj.toString();
        }
    }

    /**
     * 判断对象是否为Null或空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        boolean result = false;
        if (isNull(obj)) {
            result = true;
        } else {
            if (obj instanceof String) {
                result = ((String) obj).equals("");
            } else if (obj instanceof Date) {
                result = ((Date) obj).getTime() == 0;
            } else if (obj instanceof Long) {
                result = ((Long) obj).longValue() == Long.MIN_VALUE;
            } else if (obj instanceof Integer) {
                result = ((Integer) obj).intValue() == Integer.MIN_VALUE;
            } else if (obj.getClass().isArray()) {
                result = ((Object[]) obj).length == 0;
            } else if (obj instanceof Collection) {
                result = ((Collection<?>) obj).size() == 0;
            } else if (obj instanceof Map) {
                result = ((Map<?, ?>) obj).size() == 0;
            } else if (obj instanceof JSONObject) {
                result = !((JSONObject) obj).keys().hasNext();
            } else {
                result = obj.toString().equals("");
            }
        }

        return result;
    }

    /**
     * 判断对象不为Null或空，注意：不能判断包含数组
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
