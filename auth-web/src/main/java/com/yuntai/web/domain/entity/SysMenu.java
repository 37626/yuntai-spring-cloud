package com.yuntai.web.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author LiuErMao
 * @since 2020-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yt_sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父级菜单id
     */
    private Integer pid;

    /**
     * 菜单持有的权限
     */
    private String url;

    /**
     * 菜单等级,1是最高级，3是最低级
     */
    private Integer level;

    /**
     * 菜单图标
     */
    private String ioc;

    /**
     * 1有效，0无效
     */
    private Integer action;
    /**
     * 排序
     */
    private Integer sort;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("updateTime")
    private LocalDateTime updateTime;

    /**
     * 是否显示 1显示 0 不显示
     */
    @TableField("isShow")
    private Integer isShow;
    /**
     * 子菜单组
     */
    @TableField(exist = false)
    private List<SysMenu> menuList;


    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}
