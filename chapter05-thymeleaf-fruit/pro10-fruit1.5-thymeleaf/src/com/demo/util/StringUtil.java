package com.demo.util;

/**
 * ClassName: StringUtil
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/10 9:35
 * @Version 1.0
 */
public class StringUtil {

    public static boolean isEmpty(String str){
        return  str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
