package com.rookie.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 用户的表
 */
@Entity
@Table(name = "UserTable")
public class User {

    /**
     * id
     */
    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1, initialValue=100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Integer uId;

    /**
     * 用户名
     */
    @Column(unique = true, nullable = false)
    @NotNull
    @Length(min = 5, max = 20)
    private String uName;

    /**
     * 密码
     */
    @Column(nullable = false)
    @NotNull
    @Length(min = 5, max = 20)
    private String uPassword;

    /**
     * 邮箱
     */
    @Column(nullable = true)
    @Email
    private String uEmail;

    /**
     * 状态
     */
    @Column(nullable = false)
    private Integer uStatus;

    public User() {
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public Integer getuStatus() {
        return uStatus;
    }

    public void setuStatus(Integer uStatus) {
        this.uStatus = uStatus;
    }
}
