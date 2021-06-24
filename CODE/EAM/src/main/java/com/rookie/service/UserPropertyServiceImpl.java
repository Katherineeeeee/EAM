package com.rookie.service;

import com.rookie.dao.PropertyRepo;
import com.rookie.model.Property;
import com.rookie.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Service
public class UserPropertyServiceImpl implements UserPropertyService {

    @Resource
    PropertyRepo propertyRepo;

    /**
     * 获取页面的数量
     * @param pageSz 页面的最大大小
     * @return 页面的数量
     */
    @Override
    public int getNumOfPage(Integer pageSz) {
        //Todo
    }

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSz 页面的最大大小
     * @return 查询结果
     */
    @Override
    public List<Property> findByPage(int pageNo, int pageSz) {
        //Todo
    }

    /**
     * 添加资产
     * @param property 资产
     * @return 添加是否成功
     */
    @Override
    public boolean addProperty(Property property) {
        //Todo
    }

    /**
     * 添加资产
     * @param property 资产
     * @return 添加是否成功
     */
    public boolean addProperty1(@Valid Property property) {
        //Todo
    }

    /**
     * 通过pId查找资产
     * @param pId
     * @return
     */
    @Override
    public Property findBypId(Integer pId) {
        return propertyRepo.findBypId(pId).orElse(null);
    }

    /**
     * 查找未使用的资产
     * @param pageSz 页面的大小
     * @return
     */
    @Override
    public int getNumOfUnused(Integer pageSz) {
        //Todo
    }

    /**
     * 分页查询未使用的资产
     * @param pageNo 页号
     * @param pageSz 页面的最大数量
     * @return 查询结果
     */
    @Override
    public List<Property> findUnusedByPage(int pageNo, int pageSz) {
        //Todo
    }

    /**
     * 通过user查找用户的使用数量
     * @param pageSz 页面的最大数量
     * @param user 用户
     * @return 数量
     */
    @Override
    public int getNumOfPageByUser(int pageSz, User user) {
        //Todo
    }

    /**
     * 通过分页查询用户的使用情况
     * @param pageNo 页号
     * @param pageSz 页面的最大数量
     * @param colName 列名
     * @param user 用户
     * @return 查询结果
     */
    @Override
    public List<Property> findByPageByUser(Integer pageNo, Integer pageSz, String colName, User user) {
        //Todo
    }

}
