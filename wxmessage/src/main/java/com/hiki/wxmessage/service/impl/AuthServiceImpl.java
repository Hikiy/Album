package com.hiki.wxmessage.service.impl;

import com.hiki.wxmessage.entity.Users;
import com.hiki.wxmessage.repository.UsersRepository;
import com.hiki.wxmessage.service.AuthService;
import com.hiki.wxmessage.util.PasswordUtil;
import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    /**
     * 删除用户
     * @param uid
     * @return
     */
    @Override
    public Boolean deleteByUid(int uid) {
        try{
            usersRepository.deleteByUid(uid);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 通过uid获取用户信息
     * @param uid
     * @return
     */
    @Override
    public Users getUserByUid(int uid) {
        return usersRepository.findByUid(uid);
    }

    @Override
    public List<Users> getUsersList() {
        return usersRepository.findAll();
    }

    /**
     * 修改密码
     * @param uid
     * @param newPassword
     * @return
     */
    @Override
    public Boolean updatePassword(int uid, String oldPassword, String newPassword) {
        //检查旧密码是否正确
        Users user = usersRepository.findByUid(uid);
        String thePassHash = PasswordUtil.getPassHash(user.getUsername(), oldPassword, user.getCreated(), user.getPassSalt());
        if( !thePassHash.equals(user.getPassHash()) ){
            return false;
        }

        //生成新hash
        String passSalt = PasswordUtil.getPassSalt();
        String passHash = PasswordUtil.getPassHash(user.getUsername(), newPassword, user.getCreated(), passSalt);
        int time = (int)(new Date().getTime()/1000);

        user.setPassSalt(passSalt);
        user.setPassHash(passHash);
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
