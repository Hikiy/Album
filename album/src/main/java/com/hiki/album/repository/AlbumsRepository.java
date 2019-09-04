package com.hiki.album.repository;

import com.hiki.album.entity.Albums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：hiki
 * 2019/8/15 10:37
 */
public interface AlbumsRepository extends JpaRepository<Albums, Integer> {
    Albums findByAid(Integer aid);
    Albums findByCode(String code);
    List<Albums> findAll();
    @Transactional
    void deleteByAid(int aid);
}
