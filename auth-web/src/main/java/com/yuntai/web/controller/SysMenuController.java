package com.yuntai.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yuntai.web.domain.bo.SysMenuBo;
import com.yuntai.web.domain.bo.SysRoleBo;
import com.yuntai.web.domain.bo.SysRoleMenuBo;
import com.yuntai.web.domain.bo.SysUserBo;
import com.yuntai.web.domain.entity.SysMenu;
import com.yuntai.web.domain.entity.SysRoleMenu;
import com.yuntai.web.domain.entity.SysUser;
import com.yuntai.web.service.SysMenuService;
import com.yuntai.web.service.SysRoleMenuService;
import com.yuntai.web.service.SysUserRoleService;
import com.yuntai.web.service.impl.SysRoleMenuServiceImpl;
import io.swagger.models.auth.In;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import response.BaseResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author LiuErMao
 * @since 2020-06-18
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {
    @Autowired
    SysMenuService sysMenuService;
    @Autowired
    SysUserRoleService sysUserRoleService;
    @Autowired
    SysRoleMenuService sysRoleMenuService;

    /*
     * @Description: 新增菜单
     * @Author: ErMao
     * @Date: 2020/6/19 0019 10:36
     * @Param: * @param sysMenuBo:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping("/add")
    @ResponseBody
    public BaseResponse add(@RequestBody SysMenuBo sysMenuBo){
        return BaseResponse.ok(sysMenuService.save(sysMenuBo.setCreateTime(LocalDateTime.now())));
    }
    /*
     * @Description: 删除菜单
     * @Author: ErMao
     * @Date: 2020/6/19 0019 10:37
     * @Param: * @param menuIds:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestBody List<Integer> menuIds){
        Boolean a = true;sysRoleMenuService.update(
                new LambdaUpdateWrapper<SysRoleMenu>()
                        .in(SysRoleMenu::getMenuId,menuIds)
                        .set(SysRoleMenu::getAction,0));
        Boolean b = true;sysMenuService.update(
                new LambdaUpdateWrapper<SysMenu>()
                        .in(SysMenu::getMenuId,menuIds)
                        .set(SysMenu::getAction,0));
        return BaseResponse.ok(a&&b);
    }
    /*
     * @Description: 修改菜单
     * @Author: ErMao
     * @Date: 2020/6/19 0019 10:37
     * @Param: * @param sysMenuBo:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping("/update")
    @ResponseBody
    public BaseResponse update(@RequestBody SysMenuBo sysMenuBo){
        return BaseResponse.ok(sysMenuService.updateById(sysMenuBo));
    }

    /*
     * @Description: 获取所有菜单
     * @Author: ErMao
     * @Date: 2020/6/18 0018 16:45
     * @Param:
     * @Return: * @return: response.BaseResponse
     **/
    @GetMapping("/list")
    @ResponseBody
    public BaseResponse list(){
        return BaseResponse.ok(sysMenuService
                .MenusSwitchMenuTree(sysMenuService
                        .list(new LambdaQueryWrapper<SysMenu>()
                                .eq(SysMenu::getAction,1))));
    }
    /*
     * @Description: 获取用户的菜单树
     * @Author: ErMao
     * @Date: 2020/6/19 0019 17:46
     * @Param: * @param sysUser:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping("/getMyMenuTreeByUser")
    @ResponseBody
    public BaseResponse getMyMenuTreeByUser(@RequestBody SysUserBo sysUserBo){
        List<Integer> MenuIds = sysRoleMenuService.getMenuIdsByRoleIds(sysUserRoleService.getRoleIdsByUser(sysUserBo));
        return BaseResponse.ok(
                sysMenuService.MenusSwitchMenuTree(sysMenuService
                .list(new LambdaQueryWrapper<SysMenu>()
                        .in(SysMenu::getMenuId,MenuIds)
                        .eq(SysMenu::getAction,1))));
    }
    /*
     * @Description: 获取角色的菜单树
     * @Author: LiuErMao
     * @Date: 2020/6/21 0021 17:28
     * @Param: * @param sysRoleBo:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping("/getMenuTreeByRole")
    @ResponseBody
    public BaseResponse getMenuTreeByRole(@RequestBody SysRoleBo sysRoleBo){
        List<Integer> roleIds = new ArrayList<Integer>();
        roleIds.add(sysRoleBo.getRoleId());
        return BaseResponse.ok(sysMenuService.MenusSwitchMenuTree(sysMenuService
                .list(new LambdaQueryWrapper<SysMenu>()
                        .in(SysMenu::getMenuId,sysRoleMenuService.getMenuIdsByRoleIds(roleIds))
                        .eq(SysMenu::getAction,1))));

    }


}