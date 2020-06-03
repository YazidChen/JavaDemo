package com.yazid.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yazid
 * @date 2020/6/1 14:19
 */
@RestController
@RequestMapping("web")
public class WebController {
    private static final Logger LOG = LoggerFactory.getLogger(WebController.class);

    @GetMapping("ip")
    public Map<String, Object> ip(HttpServletRequest request) {
        LOG.info("clientIp->Param:[X-Forwarded-For:{}]", request.getHeader("X-Forwarded-For"));
        LOG.info("clientIp->Param:[X-Real-IP:{}]", request.getHeader("X-Real-IP"));
        LOG.info("clientIp->Param:[Proxy-Client-IP:{}]", request.getHeader("Proxy-Client-IP"));
        LOG.info("clientIp->Param:[WL-Proxy-Client-IP:{}]", request.getHeader("WL-Proxy-Client-IP"));
        LOG.info("clientIp->Param:[HTTP_CLIENT_IP:{}]", request.getHeader("HTTP_CLIENT_IP"));
        LOG.info("clientIp->Param:[HTTP_X_FORWARDED_FOR:{}]", request.getHeader("HTTP_X_FORWARDED_FOR"));
        LOG.info("clientIp->Param:[request.getRemoteAddr():{}]", request.getRemoteAddr());

        Map<String, Object> map = new HashMap<>();
        map.put("X-Forwarded-For", request.getHeader("X-Forwarded-For"));
        map.put("X-Real-IP", request.getHeader("X-Real-IP"));
        map.put("Proxy-Client-IP", request.getHeader("Proxy-Client-IP"));
        map.put("WL-Proxy-Client-IP", request.getHeader("WL-Proxy-Client-IP"));
        map.put("HTTP_CLIENT_IP", request.getHeader("HTTP_CLIENT_IP"));
        map.put("HTTP_X_FORWARDED_FOR", request.getHeader("HTTP_X_FORWARDED_FOR"));
        map.put("RemoteAddr", request.getRemoteAddr());
        return map;
    }
}
