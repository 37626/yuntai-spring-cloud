package com.yuntai.web.domain.bo;

import com.yuntai.web.domain.entity.SysUserRole;
import lombok.Data;

import java.util.List;

@Data
public class SysUserRoleBo extends SysUserRole {
    /**
     * 角色id
     */
    public List<Integer> RoleIds;
}
