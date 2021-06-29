package com.rookie.service;

import com.rookie.dao.ApplicationRepo;
import com.rookie.dao.PropertyRepo;
import com.rookie.dao.UserRepo;
import com.rookie.model.Application;
import com.rookie.model.Manager;
import com.rookie.model.Property;
import com.rookie.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 申请单管理的Service层
 */
@Service
public class ApplicationManageServiceImpl implements ApplicationManageService {
    @Resource
    ApplicationRepo applicationRepo;

    @Resource
    PropertyRepo propertyRepo;

    @Resource
    UserRepo userRepo;

    /**
     * 获取对应用户的申请单的页面数量
     * @param pageSz 页面大小
     * @param user 用户
     * @return 页面数量
     */
    @Override
    public int getNumOfPageAndUser(int pageSz, User user) {
        //Todo
    }

    /**
     * 通过用户来进行分页查询
     * @param pageNo 页号
     * @param pageSz 页面的大小
     * @param colName 列名
     * @param user 用户
     * @return 查询结果
     */
    @Override
    public List<Application> findByPageAndUser(int pageNo, int pageSz, String colName, User user) {
        //Todo
    }

    /**
     * 通过页面大小和资产来获取页面的数量
     * @param pageSz 页面大小
     * @param property 资产
     * @return 数量
     */
    @Override
    public int getNumOfPageByPageAndProperty(int pageSz, Property property) {
        //Todo
    }

    /**
     * 通过资产进行分页查询
     * @param pageNo 页号
     * @param pageSz 页面的最大大小
     * @param colName 列名
     * @param property 资产
     * @return 查询结果
     */
    @Override
    public List<Application> findByPageAndProperty(int pageNo, int pageSz, String colName, Property property) {
        //Todo
    }

    /**
     * 获取页面的数量
     * @param pageSz 页面大小
     * @return 数量
     */
    @Override
    public int getNumOfPage(int pageSz) {
        //Todo
    }

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSz 页面的大小
     * @return 查询结果
     */
    @Override
    public List<Application> findByPage(int pageNo, int pageSz) {
        //Todo
    }

    /**
     * 更新申请单的状态
     * @param id 申请单id
     * @param status 新的状态
     * @param manager 审核管理员
     * @return 更新成功则返回true, 否则返回false
     */
    @Override

    public boolean updateApplication(Integer id, Integer status, Manager manager) {
        //Todo
    }
}
