package com.xrt.bzj.service.mchuser.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xrt.bzj.dao.mapper.AverageUserMapper;
import com.xrt.bzj.dao.param.AverageUserParam;
import com.xrt.bzj.dao.po.AverageUser;
import com.xrt.bzj.dao.vo.AverageUserVo;
import com.xrt.bzj.service.mchuser.AverageUserService;
import org.springframework.beans.BeanUtils;
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
    public AverageUserVo findAverageUser(AverageUserParam averageUserParam) {
        AverageUser averageUser = averageUserMapper.selectById(averageUserParam.getId());
        AverageUserVo averageUserVo = new AverageUserVo();
        BeanUtils.copyProperties(averageUser,averageUserVo);
        return averageUserVo;
    }

    @Override
    public List<AverageUser> findAllAverageUser() {
        LambdaQueryWrapper<AverageUser> wrapper = new LambdaQueryWrapper<>();
        return averageUserMapper.selectList(wrapper);
    }
}
