package com.xrt.bzj.service.mchuser;

import com.xrt.bzj.dao.po.MchUser; /**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service
 * @date 2021/2/27 15:14
 */
public interface MchUserService {

    MchUser findMchUser(MchUser mchUser);
}
