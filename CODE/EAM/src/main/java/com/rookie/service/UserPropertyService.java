package com.rookie.service;

import com.rookie.model.Property;
import com.rookie.model.User;

import javax.validation.Valid;
import java.util.List;

/**
 * User查看资产的Service接口
 */
public interface UserPropertyService {

    /**
     * 获取页面的数量
     * @param pageSz 页面的最大大小
     * @return 页面的数量
     */
    int getNumOfPage(Integer pageSz);

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSz 页面的最大大小
     * @return 查询结果
     */
    List<Property> findByPage(int pageNo, int pageSz);

    /**
     * 添加资产
     * @param property 资产
     * @return 添加是否成功
     */
    boolean addProperty(Property property);

    /**
     * 添加资产
     * @param property 资产
     * @return 添加是否成功
     */
    boolean addProperty1(@Valid Property property);

    /**
     * 通过pId查找资产
     * @param pId
     * @return
     */
    Property findBypId(Integer pId);

    /**
     * 查找未使用的资产
     * @param pageSz 页面的大小
     * @return
     */
    int getNumOfUnused(Integer pageSz);

    /**
     * 分页查询未使用的资产
     * @param pageNo 页号
     * @param pageSz 页面的最大数量
     * @return 查询结果
     */
    List<Property> findUnusedByPage(int pageNo, int pageSz);

    /**
     * 通过user查找用户的使用数量
     * @param pageSz 页面的最大数量
     * @param user 用户
     * @return 数量
     */
    int getNumOfPageByUser(int pageSz, User user);

    /**
     * 通过分页查询用户的使用情况
     * @param pageNo 页号
     * @param pageSz 页面的最大数量
     * @param colName 列名
     * @param user 用户
     * @return 查询结果
     */
    List<Property> findByPageByUser(Integer pageNo, Integer pageSz, String colName, User user);
}
