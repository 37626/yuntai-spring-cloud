package com.yuntai.web.service;

import com.yuntai.web.domain.entity.SysUser;
import com.yuntai.web.domain.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色中间表 服务类
 * </p>
 *
 * @author LiuErMao
 * @since 2020-06-18
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    /*
     * @Description: 根据用户查询角色id
     * @Author: LiuErMao
     * @Date: 2020/6/21 0021 17:49
     * @Param: * @param sysUser:
     * @Return: * @return: java.util.List<java.lang.Integer>
     **/
    List<Integer> getRoleIdsByUser(SysUser sysUser);
}
