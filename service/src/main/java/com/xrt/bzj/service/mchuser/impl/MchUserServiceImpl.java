package com.xrt.bzj.service.mchuser.impl;

import com.xrt.bzj.dao.mapper.MchUserMapper;
import com.xrt.bzj.dao.po.MchUser;
import com.xrt.bzj.service.mchuser.MchUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service.mchuser.impl
 * @date 2021/2/27 16:36
 */
@Service
public class MchUserServiceImpl implements MchUserService {

    @Autowired
    MchUserMapper mchUserMapper;

    @Override
    public MchUser findMchUser(MchUser mchUser) {
        return mchUserMapper.selectByPrimaryKey(mchUser.getId());
    }
}
