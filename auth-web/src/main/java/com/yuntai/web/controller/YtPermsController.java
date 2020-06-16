package com.yuntai.web.controller;


import com.yuntai.web.base.AjaxResult;
import com.yuntai.web.domain.bo.PermsBo;
import com.yuntai.web.domain.bo.PermsRoleBo;
import com.yuntai.web.domain.entity.YtPerms;
import com.yuntai.web.service.impl.YtPermsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/yt-perms")
public class YtPermsController {
    @Autowired
    YtPermsServiceImpl permsService;
    /*
     * @Description: 获取权限列表
     * @Author: ErMao
     * @Date: 2020/6/16 0016 14:56
     * @Param:
     * @Return: * @return: com.yuntai.web.base.AjaxResult
     **/
    public AjaxResult listPerms(){
        List<YtPerms> list =  permsService.list();
        AjaxResult ar = new AjaxResult();
        ar.setData(list);
        return ar;
    }
    /*
     * @Description: 获取权限
     * @Author: ErMao
     * @Date: 2020/6/16 0016 15:11
     * @Param: * @param permsRoleBo: 角色id组
     * @Return: * @return: com.yuntai.web.base.AjaxResult
     **/
    @PostMapping("/getPerms")
    @ResponseBody
    public AjaxResult getPerms(@RequestBody PermsRoleBo permsRoleBo){
       //List<YtPerms> list =  permsService.getBaseMapper().getPerms(permsRoleBo.getRoleIds());
       AjaxResult ar = new AjaxResult();
       //ar.setData(list);
       return ar;
    }
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@RequestBody PermsBo perms){
        AjaxResult ar = new AjaxResult();
        Long updateTime = System.currentTimeMillis();
        perms.setUpdateTime(updateTime);
        permsService.updateById(perms);
        ar.setDesc("修改成功");
        return ar;
    }
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(@RequestBody  PermsBo perms){
        Long  createTime= System.currentTimeMillis();
        perms.setCreateTime(createTime);
        permsService.save(perms);
        AjaxResult ar = new AjaxResult();
        ar.setDesc("新增成功");
        return ar;

    }
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestBody List<String> permsIds){
        AjaxResult ar = new AjaxResult();
        return ar;
    }

}
