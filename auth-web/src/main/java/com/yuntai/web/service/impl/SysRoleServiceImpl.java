package com.yuntai.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuntai.web.domain.entity.SysRole;
import com.yuntai.web.domain.entity.SysRoleMenu;
import com.yuntai.web.domain.entity.SysUser;
import com.yuntai.web.domain.entity.SysUserRole;
import com.yuntai.web.mapper.SysRoleMapper;
import com.yuntai.web.service.SysRoleMenuService;
import com.yuntai.web.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuntai.web.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author LiuErMao
 * @since 2020-06-18
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

}
