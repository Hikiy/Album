package com.hiki.wxmessage.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ：hiki
 * 2019/8/6 10:21
 */
@Entity
@Data
public class AlbumCategory implements Serializable {
    private static final long serialVersionUID = -3240292504277204917L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int acid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String banner;

    @Column
    private int status;

    @Column(nullable = false)
    private int created;

    @Column(nullable = false)
    private int updated;
}
