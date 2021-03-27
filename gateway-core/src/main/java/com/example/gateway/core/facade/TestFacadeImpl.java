package com.example.gateway.core.facade;

import com.example.gateway.api.facade.TestFacade;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 测试
 *
 * @author Yiren (<a href="mailto:lf253130@alibaba-inc.com">Send Email.<a/>)
 * @date 2021/3/20
 * @since 1.0.0
 */
@DubboService()
public class TestFacadeImpl implements TestFacade {
    @Override
    public String hello(String name) {
        return "hello, " + name;
    }

}
