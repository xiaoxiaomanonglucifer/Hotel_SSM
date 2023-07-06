package per.zpp.utils;

import java.util.UUID;

/**
 * @Author 想去外太空的
 * @Date 2023/6/3 16:43
 * @Version 1.0 （版本号）
 */
public class UUIDUtils {
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
