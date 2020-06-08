package com.yuntai.web.service;

import com.yuntai.web.domain.entity.YtUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuntai
 * @since 2020-05-29
 */
public interface YtUserService extends IService<YtUser> {

    @Transactional
    void testTransaction(YtUser user);

}
