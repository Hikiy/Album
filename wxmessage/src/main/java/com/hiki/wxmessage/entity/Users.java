package com.hiki.wxmessage.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ：hiki
 * 2019/8/16 16:18
 */
@Entity
@Data
public class Users implements Serializable {
    private static final long serialVersionUID = -4309072564161374460L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String passSalt;

    @Column(nullable = false)
    private String passHash;

    @Column
    private int status;

    @Column(nullable = false)
    private int created;

    @Column(nullable = false)
    private int updated;
}
