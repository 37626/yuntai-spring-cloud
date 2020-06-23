package com.yuntai.web.base;

import com.sun.org.glassfish.gmbal.Description;
import lombok.Data;

import java.util.List;

/**
 * 返回对象
 */
@Data
public class YtCollectionUtils {

    public static Boolean ifListLengthZeroAndNull(List list){
        if(list==null||list.isEmpty()){
            return true;
        }
        return false;
    }


}
