package com.yuntai.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuntai.web.domain.entity.YtUser;
import com.yuntai.web.mapper.YtUserMapper;
import com.yuntai.web.service.YtUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Description: 类描述
 * @Author: yuanye 
 * @Company: yuntai
 * @Date: 2020/6/12 4:57 下午
 **/
@Service
public class YtUserServiceImpl extends ServiceImpl<YtUserMapper, YtUser> implements YtUserService {

    /**
    * @Description: 添加
    * @Param: * @param user:
    * @Return: * @return: com.yuntai.web.domain.entity.YtUser
    * @Author: 袁野
    * @Date: 2020年06月12日
    */
    @Override
    public YtUser add(YtUser user) {
        this.save( user );
        return user;
    }

   /*
    * @Description: 方法注释
    * @Author: YuanYe 
    * @Date: 2020/6/12 4:57 下午
    * @Param: * @param id:
    * @Return: * @return: java.lang.Boolean
    **/
    @Override
    public Boolean delete(Integer id) {
        this.delete( id );
        return true;
    }

    /**
    * @Description: 更改一个
    * @Param: * @param user:
    * @Return: * @return: java.lang.Boolean
    * @Author: 袁野
    * @Date: 2020年06月12日
    */
    @Override
    public Boolean updateOne(YtUser user) {
        this.updateById(user);
        return true;
    }

    /**
    * @Description: 获取详情
    * @Param: * @param id:
    * @Return: * @return: com.yuntai.web.domain.entity.YtUser
    * @Author: 袁野
    * @Date: 2020年06月12日
    */
    @Override
    public YtUser getDetail(Integer id) {
        return this.getById( id );
    }

    /**
    * @Description:  获取列表
    * @Param: * @param user:
    * @Return: * @return: java.util.List<com.yuntai.web.domain.entity.YtUser>
    * @Author: 袁野
    * @Date: 2020年06月12日
    */
    @Override
    public List<YtUser> getList(YtUser user) throws Exception {
        LambdaQueryWrapper<YtUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq( YtUser::getAccount,user.getAccount());
        return this.list( queryWrapper );
    }
}
