package indi.blogtest.util;

import java.util.HashMap;
import java.util.Map;

public class ResultUtils {

    public static Integer SUCCESS = 2000;
    public static Integer ERROR = 2001;
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();
    private ResultUtils(){}
    public static ResultUtils ok(){
        ResultUtils r = new ResultUtils();
        r.success = true;
        r.code = SUCCESS;
        r.message = "success";
        return r;
    }
    public static ResultUtils ok(String message){
        ResultUtils r = new ResultUtils();
        r.success = true;
        r.code = SUCCESS;
        r.message = message;
        return r;
    }
    public static ResultUtils ok(Map<String, Object> data){
        ResultUtils r = ok();
        r.setData(data);
        return r;
    };
    public static ResultUtils ok(Object data){
        ResultUtils r = ok();
        Map<String, Object> container = new HashMap<>();
        container.put("data", data);
        r.setData(container);
        return r;
    }
    public static ResultUtils error(){
        ResultUtils r = new ResultUtils();
        r.success = false;
        r.code = ERROR;
        r.message = "error";
        return r;
    }

    public static ResultUtils error(String message){
        ResultUtils r = new ResultUtils();
        r.success = false;
        r.code = ERROR;
        r.message = message;
        return r;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
