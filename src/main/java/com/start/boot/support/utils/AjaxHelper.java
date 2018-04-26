package com.start.boot.support.utils;

public class AjaxHelper {

    /**
     * 序列化AjaxResponseData中数据为json
     * @param ErrMsg 异常信息
     * @param data 数据
     * @return JSON字符串
     */
    public static <T> String buildResponseDataSource(String ErrMsg, T data){
        String json = "";

        try{
            AjaxResponseData<T> responseData = new AjaxResponseData<T>(ErrMsg, data);

            json = FastJsonUtils.toString(responseData);//JsonHelper.jsonSerialize(responseData);
        }catch (Exception e) {
            json = String.format("ErrMsg:{0}", e.getMessage());
        }

        return json;
    }

}
