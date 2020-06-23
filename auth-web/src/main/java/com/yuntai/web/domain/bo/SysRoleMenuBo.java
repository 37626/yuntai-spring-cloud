package com.yuntai.web.domain.bo;

import com.yuntai.web.domain.entity.SysMenu;
import com.yuntai.web.domain.entity.SysRoleMenu;
import lombok.Data;

import java.util.List;
@Data
public class SysRoleMenuBo extends SysRoleMenu {
    /**
     * 菜单id数组
     */
    public List<Integer> menuIds;
}
