package com.hiki.wxmessage.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ：hiki
 * 2019/8/15 10:27
 */
@Entity
@Data
public class Albums implements Serializable {
    private static final long serialVersionUID = -512604726899929870L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String banner;

    @Column(nullable = false)
    private int updated;
}
