package com.hiki.wxmessage.service.impl;

import com.hiki.wxmessage.entity.Users;
import com.hiki.wxmessage.repository.UsersRepository;
import com.hiki.wxmessage.service.AuthService;
import com.hiki.wxmessage.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ：hiki
 * 2019/8/16 16:37
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UsersRepository usersRepository;

    /**
     * 通过username获取用户信息
     * @param username
     * @return
     */
    @Override
    public Users getUserByUsername(String username) {
        return usersRepository.findAllByUsername(username);
    }

    /**
     * 检查账号密码是否正确
     * @param username
     * @param password
     * @param created
     * @param passSalt
     * @param passHash
     * @return
     */
    @Override
    public Boolean checkPassword(String username, String password, int created, String passSalt, String passHash) {

        String correct = PasswordUtil.getPassHash(username, password, created, passSalt);
        if( correct.equals(passHash) ){
            return true;
        }

        return false;
    }

    /**
     *
     * @param username
     * @param password
     * @param name
     * @return
     */
    @Override
    public Boolean register(String username, String password, String name) {
        int time = (int)(new Date().getTime()/1000);
        String passSalt = PasswordUtil.getPassSalt();
        String passHash = PasswordUtil.getPassHash(username, password, time, passSalt);

        Users user = new Users();
        user.setUsername(username);
        user.setName(name);
        user.setPassSalt(passSalt);
        user.setPassHash(passHash);
        user.setStatus(1);
        user.setCreated(time);
        user.setUpdated(time);

        try {
            usersRepository.save(user);
        }catch (Exception e){
            return false;
        }

        if(user.getUid() < 1){
            return false;
        }
        return true;
    }
}
