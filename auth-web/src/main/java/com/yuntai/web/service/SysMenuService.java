package com.yuntai.web.service;

import com.yuntai.web.domain.bo.SysRoleMenuBo;
import com.yuntai.web.domain.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuntai.web.domain.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author LiuErMao
 * @since 2020-06-18
 */
public interface SysMenuService extends IService<SysMenu> {
     /*
      * @Description: 将菜单数组转换为菜单树
      * @Author: LiuErMao
      * @Date: 2020/6/21 0021 17:48
      * @Param: * @param menus:
      * @Return: * @return: java.util.List<com.yuntai.web.domain.entity.SysMenu>
      **/
     List<SysMenu> MenusSwitchMenuTree(List<SysMenu> menus);
}
