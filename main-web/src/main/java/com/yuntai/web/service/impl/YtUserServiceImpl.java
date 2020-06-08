package com.yuntai.web.service.impl;

import com.yuntai.web.domain.entity.YtUser;
import com.yuntai.web.mapper.YtUserMapper;
import com.yuntai.web.service.YtUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuntai
 * @since 2020-05-29
 */
@Service
public class YtUserServiceImpl extends ServiceImpl<YtUserMapper, YtUser> implements YtUserService {

    @Autowired
    YtUserMapper ytUserMapper;

    @Transactional
    @Override
    public void testTransaction(YtUser user) {
        ytUserMapper.insert( user );
    }
}
