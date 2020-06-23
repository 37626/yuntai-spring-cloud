package com.yuntai.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yuntai.web.base.YtCollectionUtils;
import com.yuntai.web.domain.bo.SysRoleBo;
import com.yuntai.web.domain.bo.SysUserBo;
import com.yuntai.web.domain.bo.SysUserRoleBo;
import com.yuntai.web.domain.entity.SysRole;
import com.yuntai.web.domain.entity.SysUser;
import com.yuntai.web.domain.entity.SysUserRole;
import com.yuntai.web.service.SysRoleService;
import com.yuntai.web.service.SysUserRoleService;
import com.yuntai.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import response.BaseResponse;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author LiuErMao
 * @since 2020-06-18
 */
@Controller
@RequestMapping("/sys-user")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysUserRoleService sysUserRoleService;


    /*
     * @Description: 添加用户
     * @Author: ErMao
     * @Date: 2020/6/19 0019 15:42
     * @Param: * @param sysRoleBo:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping("/add")
    @ResponseBody
    public BaseResponse add(@RequestBody SysUserBo sysUserBo){
        return BaseResponse.ok(sysUserService.save(sysUserBo.setCreateTime(LocalDateTime.now())));
    }
    /*
     * @Description: 删除用户
     * @Author: ErMao
     * @Date: 2020/6/19 0019 15:53
     * @Param: * @param userIds:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestBody List<Integer> userIds){
        return BaseResponse.ok(sysUserService
                .update(new LambdaUpdateWrapper<SysUser>()
                        .in(SysUser::getUserId,userIds)
                        .set(SysUser::getAction,0)));
    }
    /*
     * @Description: 修改用户
     * @Author: ErMao
     * @Date: 2020/6/19 0019 15:53
     * @Param: * @param sysUserBo:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping("/update")
    @ResponseBody
    public BaseResponse update(@RequestBody SysUserBo sysUserBo){
        return BaseResponse.ok(sysUserService.updateById(sysUserBo));
    }
    /*
     * @Description: 查询用户
     * @Author: ErMao
     * @Date: 2020/6/19 0019 15:52
     * @Param:
     * @Return: * @return: response.BaseResponse
     **/
    @GetMapping("/list")
    @ResponseBody
    public BaseResponse list(){
        return BaseResponse.ok(sysUserService.list(new LambdaQueryWrapper<SysUser>().eq(SysUser::getAction,1)));
    }
    /*
     * @Description: 修改角色
     * @Author: LiuErMao
     * @Date: 2020/6/21 0021 17:05
     * @Param: * @param sysUserRoleBo:
     * @Return: * @return: response.BaseResponse
     **/
    @PostMapping("/modifyRole")
    @ResponseBody
    public BaseResponse modifyRole(@RequestBody SysUserRoleBo sysUserRoleBo){
        Integer userId = sysUserRoleBo.getUserId();
        List<Integer> roleIds = sysUserRoleBo.getRoleIds();
        //
        if(YtCollectionUtils.ifListLengthZeroAndNull(sysUserRoleBo.getRoleIds())){
            sysUserRoleService.update(
                    new LambdaUpdateWrapper<SysUserRole>()
                            .eq(SysUserRole::getUserId,userId)
                            .set(SysUserRole::getAction,0));
            return BaseResponse.ok();
        }
        //添加新角色
        roleIds.forEach(roleid ->{
            sysUserRoleService.saveOrUpdate(sysUserRoleBo.setRoleId(roleid).setCreateTime(LocalDateTime.now()));
        });
        //删除用户的所有角色
        sysUserRoleService.update(new LambdaUpdateWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId,sysUserRoleBo.getUserId())
                .set(SysUserRole::getAction,0));
        //赋予新角色
        sysUserRoleService.update(new LambdaUpdateWrapper<SysUserRole>()
                .in(SysUserRole::getRoleId,roleIds).eq(SysUserRole::getUserId,userId)
                .set(SysUserRole::getAction,1));
        return BaseResponse.ok();
    }

}
