package com.hiki.album.repository;

import com.hiki.album.entity.AlbumCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：hiki
 * 2019/8/6 10:57
 */
public interface AlbumCategoryRepository extends JpaRepository<AlbumCategory, Integer> {
    List<AlbumCategory> findAll();

    @Query(value = "select acid, banner from AlbumCategory")
    List<Object> findBannerList();

    List<AlbumCategory> findAllByAidOrderByPriorityDesc(int aid);

    AlbumCategory findByAcid(Integer acid);
    List<AlbumCategory> findByCode(String name);

    @Transactional
    void deleteByAcid(int acid);
}
