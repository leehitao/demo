package com.xrt.bzj.service.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service.handle
 * @date 2022/8/13 16:11
 */
@Component
public class MsgHandlerFactory {

    @Autowired
    private EmailHandler emailHandler;

    @Autowired
    private Map<String, MsgHandler> handlerMapping;

    @PostConstruct
    public void init() {
        handlerMapping.put("1", emailHandler);
    }

    public MsgHandler createHandler(String type) {
        return handlerMapping.get(type);
    }
}
