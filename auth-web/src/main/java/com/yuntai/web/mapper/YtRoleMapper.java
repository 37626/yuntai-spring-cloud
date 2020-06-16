package com.yuntai.web.mapper;

import com.yuntai.web.domain.entity.YtRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuntai
 * @since 2020-06-16
 */
public interface YtRoleMapper extends BaseMapper<YtRole> {
    /*
     * @Description: 根据账户查询角色
     * @Author: ErMao
     * @Date: 2020/6/16 0016 10:41
     * @Param: * @param null:
     * @Return: * @return: null
     **/
    public List<YtRole> getRole(String username);

}
