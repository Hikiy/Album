package com.hiki.wxmessage.service.impl;

import com.hiki.wxmessage.entity.Users;
import com.hiki.wxmessage.repository.UsersRepository;
import com.hiki.wxmessage.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：hiki
 * 2019/8/16 16:37
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public Users getUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
