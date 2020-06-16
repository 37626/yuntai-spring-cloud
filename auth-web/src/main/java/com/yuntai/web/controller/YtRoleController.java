package com.yuntai.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yuntai.web.base.AjaxResult;
import com.yuntai.web.domain.bo.RoleBo;
import com.yuntai.web.domain.entity.YtPermsRole;
import com.yuntai.web.domain.entity.YtRole;
import com.yuntai.web.domain.entity.YtRoleUser;
import com.yuntai.web.service.impl.YtPermsRoleServiceImpl;
import com.yuntai.web.service.impl.YtRoleServiceImpl;
import com.yuntai.web.service.impl.YtRoleUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuntai
 * @since 2020-06-16
 */

@Controller
@RequestMapping("/yt-role")
public class YtRoleController {
    @Autowired
    YtRoleServiceImpl roleService;
    @Autowired
    YtRoleUserServiceImpl permsRoleService;

    
    /*
     * @Description: 查询所有角色
     * @Author: ErMao 
     * @Date: 2020/6/16 0016 10:31
     * @Param: 
     * @Return: * @return: com.yuntai.web.base.AjaxResult
     **/
    @GetMapping("/listRole")
    @ResponseBody
    public AjaxResult listRole(){
        List<YtRole> list =  roleService.list();
        return new AjaxResult(list);
    }
    /*
     * @Description:查询角色
     * @Author: ErMao
     * @Date: 2020/6/16 0016 10:38
     * @Param: * @param username:用户id
     * @Return: * @return: com.yuntai.web.base.AjaxResult
     **/
    @PostMapping("/getRole")
    @ResponseBody
    public AjaxResult getRole(String username){
        List<YtRole> list = roleService.getBaseMapper().getRole(username);
        return new AjaxResult(list);
    }
    /*
     * @Description: 删除角色
     * @Author: ErMao
     * @Date: 2020/6/16 0016 10:37
     * @Param:
     * @Return: * @return: com.yuntai.web.base.AjaxResult
     **/
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult delete(@RequestBody List<String> idList){
        AjaxResult ar = new AjaxResult();
        System.err.println(idList.toString());
        //删除中间表的数据
       Boolean b =  permsRoleService.remove(new QueryWrapper<YtRoleUser>().in(true,"role_id",idList));
        //删除角色表的数据
        roleService.remove(new QueryWrapper<YtRole>().in(true,"role_id",idList));
       ar.setDesc("删除成功");
        return ar;
    }
    /*
     * @Description: 修改角色
     * @Author: ErMao
     * @Date: 2020/6/16 0016 10:38
     * @Param:
     * @Return: * @return: com.yuntai.web.base.AjaxResult
     **/
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@RequestBody RoleBo roleBo){
        AjaxResult ar = new AjaxResult();
        Long updateTime = System.currentTimeMillis();
        roleBo.setUpdateTime(updateTime);
        System.err.println(roleBo.toString());
        roleService.updateById(roleBo);
        ar.setDesc("修改成功");
        return  ar  ;
    }
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(@RequestBody  RoleBo roleBo){
        AjaxResult ar = new AjaxResult();
        System.err.println(roleBo.toString());
        Long createTime = System.currentTimeMillis();
        roleBo.setCreateTime(createTime);
        roleService.save(roleBo);
        ar.setDesc("新增成功");
        return  ar  ;
    }

}
