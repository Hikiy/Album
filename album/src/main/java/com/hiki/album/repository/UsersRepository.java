package com.hiki.album.repository;

import com.hiki.album.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：hiki
 * 2019/8/16 16:22
 */
public interface UsersRepository extends JpaRepository<Users, Integer> {
    public List<Users> findAll();

    @Query(value = "select uid, username, name, passSalt, passHash, created from Users where status = 1")
    public Users findByUsername(String username);

    public Users findAllByUsername(String username);

    public Users findByUid(int uid);

    @Transactional
    public void deleteByUid(int uid);
}