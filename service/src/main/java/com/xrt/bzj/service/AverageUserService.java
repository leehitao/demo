package com.xrt.bzj.service;

import com.xrt.bzj.dao.entity.AverageUser;
import com.xrt.bzj.dao.param.AverageUserParam;
import com.xrt.bzj.dao.vo.AverageUserVo;

import java.util.List;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service
 * @date 2021/2/27 15:14
 */
public interface AverageUserService {

    AverageUserVo findAverageUser(AverageUserParam averageUser);

    List<AverageUser> findAllAverageUser();

}
