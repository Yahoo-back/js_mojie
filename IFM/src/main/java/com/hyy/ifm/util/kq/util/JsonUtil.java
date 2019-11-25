package com.hyy.ifm.util.kq.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    /**
     * 得到json中string类型的值
     * 
     * @param jo
     * @param key
     * @return
     */
    public static String getJString(JSONObject jo, String key, String defaultVal)
    {
        if (jo == null)
        {
            return defaultVal;
        }
        if (jo.containsKey(key))
        {
            return "".equals(jo.get(key).toString()) ? defaultVal : jo.get(key).toString();
        }
        return defaultVal;
    }

    /**
     * 从JSON对象中获取字符串并做必要性判断
     * 
     * @param jsonObject
     * @param key
     * @param detail
     * @return
     */
    public static String getJStringAndCheck(JSONObject jsonObject, String key, JSONObject detail)
    {
        return getJStringAndCheck(jsonObject, key, null, true, detail, ErrorCode.ERROR_NOTE_TEMPLATE);
    }

    public static String getJStringAndCheck(JSONObject jsonObject, String key, boolean required, JSONObject detail)
    {
        return getJStringAndCheck(jsonObject, key, null, required, detail, ErrorCode.ERROR_NOTE_TEMPLATE);
    }

    public static String getJStringAndCheck(JSONObject jsonObject, String key, String defaultVal, boolean required, JSONObject detail)
    {
        return getJStringAndCheck(jsonObject, key, defaultVal, required, detail, ErrorCode.ERROR_NOTE_TEMPLATE);
    }

    /**
     * 获取字符串值并做必要性检查
     * 
     * @param jsonObject
     * @param key
     * @param defaultVal
     * @param required true必填，false非必填
     * @param detail 返回的JsonObject
     * @param errorNoteTemp 必要性检查报错的模版
     * @return
     */
    public static String getJStringAndCheck(JSONObject jsonObject, String key, String defaultVal, boolean required, JSONObject detail, String errorNoteTemp)
    {
        String defaultValue = "";
        if (defaultVal != null)
        {
            defaultValue = defaultVal;
        }
        String jString = (jsonObject != null && jsonObject.containsKey(key) && !Utils.isBnull(jsonObject.get(key).toString().trim())) ? jsonObject.get(key).toString().trim() : defaultValue;
        if (required)
        {
            if (Utils.isBnull(jString))
            {
                detail.put(Consts.RESULT, ErrorCode.FAILED);
                if (errorNoteTemp.contains("${filedName}"))
                {
                    detail.put(Consts.RESULT_NOTE, errorNoteTemp.replace("${filedName}", key));
                }
                else
                {
                    detail.put(Consts.RESULT_NOTE, errorNoteTemp);
                }
            }
        }
        return (jsonObject != null && jsonObject.containsKey(key) && !Utils.isBnull(jsonObject.get(key).toString().trim())) ? jsonObject.get(key).toString().trim() : defaultValue;
    }

    /**
     * 以int形式返回JSONObject中的值
     */
    public static Integer getJInt(JSONObject JSONObject, String key, Integer defaultVal)
    {
        Integer defaultValue = 0;
        if (defaultVal != null)
        {
            defaultValue = defaultVal;
        }
        return JSONObject.containsKey(key) ? ("".equals(JSONObject.getString(key)) ? defaultValue : JSONObject.getInteger(key)) : defaultValue;
    }

    public static Integer getJInt(JSONObject JSONObject, String key)
    {
        return getJInt(JSONObject, key, null);
    }

    /**
     * 以int形式返回JSONObject中的值
     */
    public static Integer getJIntDefault(JSONObject JSONObject, String key, Integer defaultVal)
    {
        return JSONObject.getInteger(key) == null ? defaultVal : JSONObject.getInteger(key);
    }

    public static Integer getJIntAndCheck(JSONObject jsonObject, String key, Integer defaultVal, boolean required, JSONObject detail)
    {
        return getJIntAndCheck(jsonObject, key, defaultVal, required, detail, ErrorCode.ERROR_NOTE_TEMPLATE);
    }

    /**
     * 获取字符串值并做必要性检查
     * 
     * @param jsonObject
     * @param key
     * @param defaultVal
     * @param required true必填，false非必填
     * @param detail 返回的JsonObject
     * @param errorNoteTemp 必要性检查报错的模版
     * @return
     */
    public static Integer getJIntAndCheck(JSONObject jsonObject, String key, Integer defaultVal, boolean required, JSONObject detail, String errorNoteTemp)
    {
        Integer jInt = getJInt(jsonObject, key, defaultVal);
        if (required)
        {
            if (Utils.isBnull(jInt))
            {
                detail.put(Consts.RESULT, ErrorCode.FAILED);
                if (errorNoteTemp.contains("${filedName}"))
                {
                    detail.put(Consts.RESULT_NOTE, errorNoteTemp.replace("${filedName}", key));
                }
                else
                {
                    detail.put(Consts.RESULT_NOTE, errorNoteTemp);
                }
            }
        }
        return jInt;
    }

    public static Double getJDouble(JSONObject JSONObject, String key)
    {
        return getJDouble(JSONObject, key, null);
    }

    /**
     * 以Double形式返回JSONObject中的值
     */
    public static Double getJDouble(JSONObject JSONObject, String key, Double defaultVal)
    {
        Double defaultValue = 0.0;
        if (defaultVal != null)
        {
            defaultValue = defaultVal;
        }
        return JSONObject.containsKey(key) ? ("".equals(JSONObject.getString(key)) ? defaultValue : JSONObject.getDouble(key)) : defaultValue;
    }

    /**
     * JSONArray排序
     * 
     * @param mJSONArray
     * @param field 排序字段
     * @param order 排序：desc降序 asc 升序
     * @param fileType 字段类型：string ,integer
     */
    public static void sortJsonArray(JSONArray mJSONArray, String field, String order, String fileType)
    {
        List<JSONObject> list = new ArrayList<JSONObject>();
        JSONObject jsonObj = null;
        for (int i = 0; i < mJSONArray.size(); i++)
        {
            jsonObj = mJSONArray.getJSONObject(i);
            list.add(jsonObj);
        }
        // 排序操作
        JSONComparator pComparator = new JSONComparator(field, order, fileType);
        Collections.sort(list, pComparator);
        // 把数据放回去
        mJSONArray.clear();
        for (int i = 0; i < list.size(); i++)
        {
            jsonObj = list.get(i);
            mJSONArray.add(jsonObj);
        }
    }

//    /**
//     * 将smsbean转化为jsonobject
//     *
//     * @param bean
//     * @return
//     */
//    public static JSONObject covertSms(SmsBean bean){
//        JSONObject reqParam = new JSONObject();
//        reqParam.put("cmd", CommonVal.SMS_METHOD);
//        reqParam.put("token", "245Y7BSfDHIWEie34");
//        reqParam.put("version", "1.0");
//        reqParam.put("params", JSONObject.toJSON(bean));
//        return reqParam;
//    }

    public static void setErrorCodeMsg(JSONObject detail, int errCode, String msg) {
        detail.put(Consts.RESULT, errCode);
        detail.put(Consts.RESULT_NOTE, msg);
    }

    /**
     * 设置错误消息
     *
     * @param detail
     * @param msg
     */
    public static void setErrorMsg(JSONObject detail, String msg) {
        setErrorCodeMsg(detail, ErrorCode.FAILED, msg);
    }

}
