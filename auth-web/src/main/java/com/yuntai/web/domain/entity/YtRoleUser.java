package com.yuntai.web.domain.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class YtRoleUser extends Model<YtRoleUser> {

    private static final long serialVersionUID = 1L;

    private String roleId;

    private String username;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
