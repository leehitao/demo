package com.xrt.bzj.service.file;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service.file
 * @date 2021/5/23 19:31
 */
public interface FileDownloadService {

    void downloadUserInfo(HttpServletResponse response, Integer type);

    void downloadUserInfoMix(HttpServletResponse response, Integer type);
}
