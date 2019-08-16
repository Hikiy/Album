package com.hiki.wxmessage.service;

import com.hiki.wxmessage.entity.Users;

/**
 * @author ：hiki
 * 2019/8/16 16:27
 */
public interface AuthService {
    public Users getUserByUsername(String username);
}
