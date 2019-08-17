package com.hiki.wxmessage.repository;

import com.hiki.wxmessage.entity.Photos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：hiki
 * 2019/8/7 10:33
 */
public interface PhotosRepository extends JpaRepository<Photos, Integer> {
    public List<Photos> findAllByAcid(int acid);

    public Photos findByPid(int pid);

    @Transactional
    public void deleteByPid(int pid);
}
