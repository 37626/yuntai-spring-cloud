package com.yuntai.web.base;

import com.sun.org.glassfish.gmbal.Description;
import lombok.Data;

/**
 * 返回对象
 */
@Data
public class AjaxResult {
    Integer code;
    Object data;
    String desc;
    public  AjaxResult(Object data){
    this.data = data;
    }
    public  AjaxResult(){

    }
}
