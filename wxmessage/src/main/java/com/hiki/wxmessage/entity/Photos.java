package com.hiki.wxmessage.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ：hiki
 * 2019/8/6 17:53
 */
@Entity
@Data
public class Photos implements Serializable {
    private static final long serialVersionUID = -1309800052913954361L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @Column(nullable = false)
    private int acid;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String link;

    @Column
    private int status;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private int created;
}
