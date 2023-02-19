package com.fsmile.utils;

import java.util.UUID;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.utils
 * @date 2/19/23 : 9:00 PM
 */
public class StringUtils {

    public static String uuid(){
        return UUID.randomUUID().toString();
    }
    public static String uuid(String key){
        return UUID.fromString(key).toString();
    }
}
