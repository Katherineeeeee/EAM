package com.rookie.service;

import com.rookie.model.Property;

import java.util.List;

/**
 * 资产的Service接口
 */
public interface PropertyManageService {

    /**
     * 获取页面数量
     * @param pageSz 页面的最大大小
     * @return 页面数量
     */
    int getNumOfPage(Integer pageSz);

    /**
     * 添加资产
     * @param property 资产
     * @return 添加是否成功
     */
    boolean addProperty(Property property);

    /**
     * 通过id查找资产
     * @param id
     * @return
     */
    Property findBypId(Integer id);

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSz 页面大小
     * @return 查询结果
     */
    List<Property> findByPage(int pageNo, int pageSz);
}
