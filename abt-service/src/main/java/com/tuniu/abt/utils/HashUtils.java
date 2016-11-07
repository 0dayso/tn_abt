package com.tuniu.abt.utils;

/**
 * Created by chengyao on 2016/2/5.
 */
public class HashUtils {

    public static String s() {
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex("chengyao");

    }

    public static void main(String[] args) {
        System.out.println(s());
    }

}
