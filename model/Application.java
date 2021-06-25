package com.rookie.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 申请单的表
 */
@Entity
@Table(name = "ApplicationTable")
public class Application {

    /**
     * id
     */
    @Id
    @SequenceGenerator(name = "apply_id_seq", sequenceName = "apply_id_seq", allocationSize = 1, initialValue=100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apply_id_seq")
    private Integer aId;

    /**
     * 使用的用户
     */
    @ManyToOne
    private User user;

    /**
     * 领用的资产
     */
    @ManyToOne
    private Property property;

    /**
     * 审核管理员
     */
    @ManyToOne
    private Manager manager;

    /**
     * 开始时间
     */
    @Column(nullable = false)
    private Date beginTime;

    /**
     * 审核时间
     */
    @Column(nullable = true)
    private Date reviewTime;

    /**
     * 结束时间
     */
    @Column(nullable = true)
    private Date endTime;

    /**
     * 申请单状态
     */
    @Column(nullable = false)
    private Integer aStatus;

    /**
     * 操作
     */
    @Column(nullable = false)
    private Integer operation;

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getaStatus() {
        return aStatus;
    }

    public void setaStatus(Integer aStatus) {
        this.aStatus = aStatus;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }
}
