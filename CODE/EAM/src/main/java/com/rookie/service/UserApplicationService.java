package com.rookie.service;

import com.rookie.model.Application;
import com.rookie.model.Property;
import com.rookie.model.User;

import java.util.List;

/**
 * 用户的Service接口
 */
public interface UserApplicationService {

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSz 页面数量
     * @param colName 列名
     * @param user 用户
     * @return 查询结果
     */
    List<Application> findByPageAndUser(Integer pageNo, Integer pageSz, String colName, User user);

    /**
     * 返回资产
     * @param aId
     * @param uId
     * @return
     */
    boolean returnProperty(int aId, int uId);

    /**
     * 获取页面的数量
     * @param pageSz 页面的最大大小
     * @return 页面的数量
     */
    int getNumOfPage(int pageSz);

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSz 页面的大小
     * @return 查询结果
     */
    List<Application> findByPage(int pageNo, int pageSz);

    /**
     * 添加资产
     * @param operation 操作, 当前只能是1
     * @param curUser 用户
     * @param property 资产
     * @return 添加是否成功
     */
    boolean addApplication(Integer operation, User curUser, Property property);

    /**
     * 通过property和资产来判断是否存在
     * @param curUser 用户
     * @param property 资产
     * @return 是否存在
     */
    boolean existsByUserAndProperty(User curUser, Property property);
}
