package com.rookie.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 资产的表
 */
@Entity
@Table(name = "PropertyTable")
public class Property {

    /**
     * id
     */
    @Id
    @SequenceGenerator(name = "property_id_seq", sequenceName = "property_id_seq", allocationSize = 1, initialValue=100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_id_seq")
    private Integer pId;

    /**
     * 资产名
     */
    @Column(nullable = false)
    @NotNull
    private String pName;

    /**
     * 商标
     */
    @Column(nullable = false)
    @NotNull
    private String pBrand;

    /**
     * 型号
     */
    @Column(nullable = false)
    @NotNull
    private String pModel;

    /**
     * 规格
     */
    @Column(nullable = false)
    @NotNull
    private String pSpec;

    /**
     * 采购日期
     */
    @Column(nullable = false)
    @NotNull
    private Date pTime;

    /**
     * 当前使用的用户
     */
    @ManyToOne
    private User user;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpBrand() {
        return pBrand;
    }

    public void setpBrand(String pBrand) {
        this.pBrand = pBrand;
    }

    public String getpModel() {
        return pModel;
    }

    public void setpModel(String pModel) {
        this.pModel = pModel;
    }

    public String getpSpec() {
        return pSpec;
    }

    public void setpSpec(String pSpec) {
        this.pSpec = pSpec;
    }

    public Date getpTime() {
        return pTime;
    }

    public void setpTime(Date pTime) {
        this.pTime = pTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
