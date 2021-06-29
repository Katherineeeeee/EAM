package com.rookie.service;

import com.rookie.dao.ApplicationRepo;
import com.rookie.dao.PropertyRepo;
import com.rookie.dao.UserRepo;
import com.rookie.model.Application;
import com.rookie.model.Property;
import com.rookie.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    @Resource
    ApplicationRepo applicationRepo;

    @Resource
    PropertyRepo propertyRepo;

    @Resource
    UserRepo userRepo;

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSz 页面数量
     * @param colName 列名
     * @param user 用户
     * @return 查询结果
     */
    @Override
    public List<Application> findByPageAndUser(Integer pageNo, Integer pageSz, String colName, User user) {
        //Todo
    }

    /**
     * 返回资产
     * @param aId
     * @param uId
     * @return
     */
    @Override
    public boolean returnProperty(int aId, int uId) {
        //Todo
    }

    /**
     * 获取页面的数量
     * @param pageSz 页面的最大大小
     * @return 页面的数量
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
     * 添加申请
     * @param operation 操作, 当前只能是1
     * @param curUser 用户
     * @param property 资产
     * @return 添加是否成功
     */
    @Override
    public boolean addApplication(Integer operation, User curUser, Property property) {
        //Todo
    }

    /**
     * 通过property和资产来判断是否存在
     * @param curUser 用户
     * @param property 资产
     * @return 是否存在
     */
    @Override
    public boolean existsByUserAndProperty(User curUser, Property property) {
        //Todo
    }
}
