package com.yuntai.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yuntai.web.base.YtCollectionUtils;
import com.yuntai.web.domain.bo.SysRoleBo;
import com.yuntai.web.domain.bo.SysRoleMenuBo;
import com.yuntai.web.domain.bo.SysUserBo;
import com.yuntai.web.domain.entity.SysRole;
import com.yuntai.web.domain.entity.SysRoleMenu;
import com.yuntai.web.domain.entity.SysUser;
import com.yuntai.web.domain.entity.SysUserRole;
import com.yuntai.web.service.SysRoleMenuService;
import com.yuntai.web.service.SysRoleService;
import com.yuntai.web.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import response.BaseResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.BaseStream;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author LiuErMao
 * @since 2020-06-18
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysUserRoleService sysUserRoleService;
    @Autowired
    SysRoleMenuService sysRoleMenuService;
    /*
     * @Description: 新增角色
     * @Author: ErMao
     * @Date: 2020/6/19 0019 15:04
     * @Param: * @param sysRoleBo:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping("/add")
    @ResponseBody
    public BaseResponse add(@RequestBody SysRoleBo sysRoleBo){
        return BaseResponse.ok(sysRoleService. save(sysRoleBo.setCreateTime(LocalDateTime.now())));
    }
    /*
     * @Description: 删除角色
     * @Author: ErMao
     * @Date: 2020/6/19 0019 15:04
     * @Param: * @param roleIds:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestBody List<Integer> roleIds){
        return BaseResponse.ok(sysRoleService
                .update(new LambdaUpdateWrapper<SysRole>()
                        .in(SysRole::getRoleId,roleIds)
                        .set(SysRole::getAction,0)));
    }
    /*
     * @Description: 修改角色
     * @Author: ErMao
     * @Date: 2020/6/19 0019 15:04
     * @Param: * @param sysRoleBo:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping("/update")
    @ResponseBody
    public BaseResponse update(@RequestBody SysRoleBo sysRoleBo){
        return BaseResponse.ok(sysRoleService.updateById(sysRoleBo));
    }
    /*
     * @Description: 查询全部角色
     * @Author: ErMao
     * @Date: 2020/6/19 0019 15:03
     * @Param:
     * @Return: * @return: response.BaseResponse
     **/
    @GetMapping("/list")
    @ResponseBody
    public BaseResponse list(){
        return BaseResponse.ok(sysRoleService
                .list(new LambdaQueryWrapper<SysRole>()
                        .eq(SysRole::getAction,1)));
    }
    /*
     * @Description: 查询用户拥有的角色
     * @Author: ErMao
     * @Date: 2020/6/19 0019 15:15
     * @Param: * @param sysUser:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping("/getRolesByUser")
    @ResponseBody
    public BaseResponse getRolesByUser(@RequestBody SysUserBo sysUserBo){
        List<Integer>  roleIds= sysUserRoleService.getRoleIdsByUser(sysUserBo);
        return BaseResponse.ok(sysRoleService
                .list(new LambdaQueryWrapper<SysRole>()
                        .in(SysRole::getRoleId,roleIds)
                        .eq(SysRole::getAction,1)));
    }
    /*
     * @Description: 修改权限
     * @Author: ErMao
     * @Date: 2020/6/19 0019 14:18
     * @Param: * @param sysRoleMenuBo:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping("/modifyPermission")
    @ResponseBody
    public BaseResponse modifyPermission(@RequestBody SysRoleMenuBo sysRoleMenuBo){
        Integer roleId = sysRoleMenuBo.getRoleId();
        //如果数组为空，将角色的所有权限删除
        if(YtCollectionUtils.ifListLengthZeroAndNull(sysRoleMenuBo.getMenuIds())){
            sysRoleMenuService.update(new LambdaUpdateWrapper<SysRoleMenu>()
                    .eq(SysRoleMenu::getRoleId,roleId)
                    .set(SysRoleMenu::getAction,0));
            return BaseResponse.ok();
        }
        //将权限添加到中间表
        sysRoleMenuBo.getMenuIds().forEach(menuId ->{
            sysRoleMenuService.saveOrUpdate(sysRoleMenuBo.setCreateTime(LocalDateTime.now()).setMenuId(menuId));
        });
        //删除所有权限，待定
        sysRoleMenuService.update(new LambdaUpdateWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId,roleId)
                .set(SysRoleMenu::getAction,0));
        //将数组中的权限生效
        sysRoleMenuService.update(new LambdaUpdateWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId,roleId)
                .in(SysRoleMenu::getMenuId,sysRoleMenuBo.getMenuIds())
                .set(SysRoleMenu::getAction,1));
        return BaseResponse.ok();
    }



}
