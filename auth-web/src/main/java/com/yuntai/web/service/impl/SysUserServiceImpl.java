package com.yuntai.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuntai.web.domain.entity.SysUser;
import com.yuntai.web.domain.entity.SysUserRole;
import com.yuntai.web.mapper.SysUserMapper;
import com.yuntai.web.service.SysUserRoleService;
import com.yuntai.web.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author LiuErMao
 * @since 2020-06-18
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
