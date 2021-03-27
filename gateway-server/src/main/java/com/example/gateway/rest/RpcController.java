package com.example.gateway.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rpc接口
 *
 * @author Yiren (<a href="mailto:yiren.dev@gmail.com">Send Email.<a/>)
 * @date 2021/3/21
 */
@RestController
public class RpcController {

    @RequestMapping("/rpc/{serverName}/{path}")
    public Object rpc(
            @PathVariable String serverName,
            @PathVariable String path
    ) {

        return new Object();
    }

}
