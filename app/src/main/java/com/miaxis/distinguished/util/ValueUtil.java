package com.miaxis.distinguished.util;

import com.miaxis.distinguished.model.entity.Customer;
import com.miaxis.distinguished.model.entity.MyCustomer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tang.yf on 2018/8/14.
 */

public class ValueUtil {

    public static final String SUCCESS = "200";
    public static final String FAILED = "400";
    public static final String DOWNLOAD_SUCCESS = "更新成功";
    public static final String DOWNLOAD_FAILED = "更新失败";
//    public static String ip = "62.234.202.135";
    public static String ip = "192.168.11.31";
//    public static String port = "8443";
    public static String port = "8080";

//    public static String getBaseurl() {
//        return "https://" + ip + ":" + port;
//    }

    public static String getBaseurl() {
        return "http://" + ip + ":" + port;
    }

    public static boolean isPhone(String inputText) {
        Pattern p = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }

}
