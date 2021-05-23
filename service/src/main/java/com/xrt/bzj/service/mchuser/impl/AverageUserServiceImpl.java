package com.xrt.bzj.service.mchuser.impl;

import com.xrt.bzj.dao.mapper.AverageUserMapper;
import com.xrt.bzj.dao.po.AverageUser;
import com.xrt.bzj.service.mchuser.AverageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service.mchuser.impl
 * @date 2021/2/27 16:36
 */
@Service
public class AverageUserServiceImpl implements AverageUserService {

    @Autowired
    AverageUserMapper averageUserMapper;

    @Override
    public AverageUser findAverageUser(AverageUser averageUser) {
         return averageUserMapper.selectByAccount(averageUser.getAccount());
    }

    @Override
    public List<AverageUser> findAllAverageUser() {
        return averageUserMapper.selectAllAverageUser();
    }
}
