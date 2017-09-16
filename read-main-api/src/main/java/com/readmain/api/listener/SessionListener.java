package com.readmain.api.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by yuehao on 2017/9/16.
 */

@Slf4j
@WebListener
public class SessionListener implements HttpSessionListener {


    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
//        log.info(" --------- session 被创建 -- -- - ---");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
//        log.info(" --------- session 被销毁 -- -- - ---");
    }
}
