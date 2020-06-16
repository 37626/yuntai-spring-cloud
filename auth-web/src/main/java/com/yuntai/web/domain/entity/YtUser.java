package com.yuntai.web.domain.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuntai
 * @since 2020-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YtUser extends Model<YtUser> {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String state;

    @TableField("createTime")
    private Long createTime;

    @TableField("updateTime")
    private Long updateTime;


    @Override
    protected Serializable pkVal() {
        return this.username;
    }

}
