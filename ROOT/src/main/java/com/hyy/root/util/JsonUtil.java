package com.hyy.root.util;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil
{
    /**
     * 得到json中string类型的值
     * 
     * @param jo
     * @param key
     * @return
     */
    public static String getJString(JSONObject jo, String key)
    {
        if (jo == null)
        {
            return "";
        }
        if (jo.containsKey(key))
        {
            return jo.get(key).toString();
        }
        return "";
    }

}
