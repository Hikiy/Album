package com.hiki.wxmessage.repository;

import com.hiki.wxmessage.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author ：hiki
 * 2019/8/16 16:22
 */
public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query(value = "select uid, username, name, passSalt, passHash, created from Users where status = 1")
    public Users findByUsername(String username);

    public Users findAllByUsername(String username);
}