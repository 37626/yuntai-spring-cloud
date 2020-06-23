package com.yuntai.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.yuntai.web.domain.entity.SysUser;
import com.yuntai.web.domain.entity.SysUserRole;
import com.yuntai.web.mapper.SysUserRoleMapper;
import com.yuntai.web.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户角色中间表 服务实现类
 * </p>
 *
 * @author LiuErMao
 * @since 2020-06-18
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    /*
     * @Description: 用户查询角色id
     * @Author: ErMao
     * @Date: 2020/6/19 0019 17:58
     * @Param: * @param sysUser:
     * @Return: * @return: java.util.List<java.lang.Integer>
     **/
    @Override
    public List<Integer> getRoleIdsByUser(SysUser sysUser) {
        List<Integer> list = new ArrayList<>();
        List<SysUserRole> userRoles = this.list(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId,sysUser.getUserId()));
        userRoles.forEach(userRole ->{
            list.add(userRole.getRoleId());
        });
        return list;
    }
}
