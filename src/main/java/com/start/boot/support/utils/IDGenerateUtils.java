package com.start.boot.support.utils;

import java.util.UUID;

/**
 * <h3></h3>
 *
 * @author 符黄辰君
 * @since 2017/7/12.
 */
public final class IDGenerateUtils {


    private static final String RANDOM_STRING_RANGE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String RANDOM_NUMBER_RANGE = "0123456789";

    /**
     * 获取id
     *
     * @return
     */
    public static String getCleanlyId() {
        return getId().replace("-", "");
    }

    /**
     * 获取id
     *
     * @return
     */
    public static String getId() {
        return UUID.randomUUID().toString();
    }


}


