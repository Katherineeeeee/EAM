package com.rookie.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 管理员的表
 */
@Table(name="ManagerTable")
@Entity
public class Manager {

    /**
     * id
     */
    @Id
    @SequenceGenerator(name = "manager_id_seq", sequenceName = "manager_id_seq", allocationSize = 1, initialValue=100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manager_id_seq")
    private Integer mId;

    /**
     * 姓名
     */
    @Column(unique = true, nullable = false)
    @NotNull
    @Length(min = 5, max = 20)
    private String mName;

    /**
     * 密码
     */
    @Column(nullable = false)
    @NotNull
    @Length(min = 5, max = 20)
    private String mPassword;

    /**
     * 手机号
     */
    @Column(nullable = true)
    @Pattern(regexp = "^((0\\d{2,3}-\\d{7,8})|(1[34578]\\d{9}))$")
    private String mPhone;

    /**
     * 邮箱
     */
    @Column(nullable = true)
    @Email
    private String mEmail;

    /**
     * 状态
     */
    @Column(nullable = false)
    private Integer mStatus;

    @Override
    public String toString() {
        return "Manager{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mStatus=" + mStatus +
                '}';
    }
}
