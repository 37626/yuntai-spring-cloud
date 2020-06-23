package com.yuntai.web.service.impl;

import com.yuntai.web.domain.entity.SysMenu;
import com.yuntai.web.mapper.SysMenuMapper;
import com.yuntai.web.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuntai.web.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author LiuErMao
 * @since 2020-06-18
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    SysRoleMenuService roleMenuService;

    /*
     * @Description: 将菜单装换为菜单树
     * @Author: ErMao
     * @Date: 2020/6/19 0019 18:20
     * @Param: * @param menus:
     * @Return: * @return: java.util.List<com.yuntai.web.domain.entity.SysMenu>
     **/
    public List<SysMenu> MenusSwitchMenuTree(List<SysMenu> menus){
        List<SysMenu> menuTree = new ArrayList<>();
        menus.forEach(menu ->{
            List<SysMenu> subset = new ArrayList<>();
            menus.forEach(menuSon ->{
                if(menuSon.getPid() == menu.getMenuId()){
                    subset.add(menuSon);
                }
            });
            //子菜单排序
            subset.sort(new Comparator<SysMenu>() {
                @Override
                public int compare(SysMenu o1, SysMenu o2) {
                    return o1.getSort() - o2.getSort();
                }
            });
            menu.setMenuList(subset);
            if(menu.getPid()==0){
                menuTree.add(menu);
            }
        });
        //父菜单排序
        menuTree.sort(new Comparator<SysMenu>() {
            @Override
            public int compare(SysMenu o1, SysMenu o2) {
                return o1.getSort() - o2.getSort();
            }
        });
        return menuTree;
    }

}
