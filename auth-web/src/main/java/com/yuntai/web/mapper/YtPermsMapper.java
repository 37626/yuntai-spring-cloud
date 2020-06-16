package com.yuntai.web.mapper;

import com.yuntai.web.domain.entity.YtPerms;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuntai.web.domain.entity.YtRole;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuntai
 * @since 2020-06-16
 */
public interface YtPermsMapper extends BaseMapper<YtPerms> {

    public List<YtPerms> getPerms(List<YtRole> roleList);

}
