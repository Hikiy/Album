package com.hiki.album.repository;

import com.hiki.album.entity.Photos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：hiki
 * 2019/8/7 10:33
 */
public interface PhotosRepository extends JpaRepository<Photos, Integer> {
    public List<Photos> findAllByAcidOrderByTimeDesc(int acid);

    public Photos findByPid(int pid);

    @Transactional
    public void deleteByPid(int pid);
}
