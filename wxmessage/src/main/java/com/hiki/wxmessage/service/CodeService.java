package com.hiki.wxmessage.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：hiki
 * 2019/8/17 14:23
 */
public interface CodeService {
    public Boolean checkVerify(HttpServletRequest request);
}
