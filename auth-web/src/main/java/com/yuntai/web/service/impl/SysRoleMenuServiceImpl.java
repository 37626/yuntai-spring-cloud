package com.yuntai.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yuntai.web.domain.entity.SysRoleMenu;
import com.yuntai.web.domain.entity.SysUser;
import com.yuntai.web.mapper.SysRoleMenuMapper;
import com.yuntai.web.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色菜单中间表 服务实现类
 * </p>
 *
 * @author LiuErMao
 * @since 2020-06-18
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    /*
     * @Description: 获取菜单id
     * @Author: ErMao
     * @Date: 2020/6/19 0019 18:05
     * @Param: * @param roleIds:
     * @Return: * @return: java.util.List<java.lang.Integer>
     **/
    @Override
    public List<Integer> getMenuIdsByRoleIds(List<Integer> roleIds) {
        List<Integer> menuIds = new ArrayList<>();
        List<SysRoleMenu> roleMenus = this.list(new LambdaQueryWrapper<SysRoleMenu>().in(SysRoleMenu :: getRoleId,roleIds).eq(SysRoleMenu::getAction,1));
        roleMenus.forEach(roleMenu ->{
            menuIds.add(roleMenu.getMenuId());
        });
        return menuIds;
    }
}
