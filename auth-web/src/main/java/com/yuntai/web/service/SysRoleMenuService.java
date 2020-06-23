package com.yuntai.web.service;

import com.yuntai.web.domain.entity.SysMenu;
import com.yuntai.web.domain.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuntai.web.domain.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 角色菜单中间表 服务类
 * </p>
 *
 * @author LiuErMao
 * @since 2020-06-18
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    /*
     * @Description: 根据角色id查询菜单id
     * @Author: LiuErMao
     * @Date: 2020/6/21 0021 17:48
     * @Param: * @param roleIds:
     * @Return: * @return: java.util.List<java.lang.Integer>
     **/
    List<Integer> getMenuIdsByRoleIds(List<Integer> roleIds);

}
