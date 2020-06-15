package com.yuntai.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuntai.web.domain.entity.YtUser;

import java.util.List;

/** 
* @Description: user实现接口
* @Author: 袁野
* @Company: yuntai
* @Date: 2020年06月12日 
*/ 
public interface YtUserService extends IService<YtUser> {

    /** 
    * @Description: 增加-->(后面的注释省略) 
    * @Param: * @param user:  
    * @Return: * @return: com.yuntai.web.domain.entity.YtUser 
    * @Author: 袁野
    * @Company: yuntai
    * @Date: 2020年06月12日 
    */ 
    YtUser add(YtUser user);

    /** 
    * @Description:  
    * @Param: * @param id:  
    * @Return: * @return: java.lang.Boolean 
    * @Author: 袁野
    * @Company: yuntai
    * @Date: 2020年06月12日 
    */ 
    Boolean delete(Integer id);

    /** 
    * @Description:  
    * @Param: * @param id:  
    * @Return: * @return: com.yuntai.web.domain.entity.YtUser 
    * @Author: 袁野
    * @Company: yuntai
    * @Date: 2020年06月12日 
    */ 
    YtUser getDetail(Integer id);

    /** 
    * @Description:  
    * @Param: * @param user:  
    * @Return: * @return: java.util.List<com.yuntai.web.domain.entity.YtUser> 
    * @Author: 袁野
    * @Company: yuntai
    * @Date: 2020年06月12日 
    */ 
    List<YtUser> getList(YtUser user);

    /** 
    * @Description:
    * @Param: * @param user:  
    * @Return: * @return: java.lang.Boolean 
    * @Author: 袁野
    * @Company: yuntai
    * @Date: 2020年06月12日 
    */ 
    Boolean updateOne(YtUser user);
}
