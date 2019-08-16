package com.hiki.wxmessage.repository;

import com.hiki.wxmessage.entity.Albums;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ：hiki
 * 2019/8/15 10:37
 */
public interface AlbumsRepository extends JpaRepository<Albums,Integer> {
    Albums findByAid(Integer aid);
    Albums findByCode(String code);
    List<Albums> findAll();
}
