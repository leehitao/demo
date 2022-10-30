package com.xrt.bzj.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service.file
 * @date 2021/5/23 19:31
 */
public interface FileDownloadService {

    void downloadUserInfo(HttpServletResponse response);

    void downloadUserInfoMix(HttpServletResponse response);

    void downloadUserInfoMixByExcutor(HttpServletResponse response);

    void zipDownload(HttpServletResponse response) throws IOException;
}
