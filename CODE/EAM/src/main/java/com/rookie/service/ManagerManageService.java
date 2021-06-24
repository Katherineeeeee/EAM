package com.rookie.service;

import com.rookie.model.Manager;

import java.util.List;

public interface ManagerManageService {

    /**
     * 更新manager自己
     * @param self manager
     * @return 是否更新成功
     */
    boolean updateSelf(Manager self);

    /**
     * 通过id删除manager
     * @param id manager的id
     * @return 是否更新成功
     */
    boolean delManagerById(int id);

    /**
     * 更新manager的状态
     * @param id manager的id
     * @param status 新的status
     * @return 是否更新成功
     */
    boolean updateManager(Integer id, Integer status);

    /**
     * 验证账号密码
     * @param username
     * @param password
     * @return
     */
    int verify(String username, String password);

    /**
     * 添加manager
     * @param parse
     * @return
     */
    boolean addManager(Manager parse);

    /**
     * 获取页面的数量
     * @param pageSz 页面的最大数量
     * @return 页面数量
     */
    int getNumOfPage(int pageSz);

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSz 页面大小
     * @param mId 列名
     * @return 查询结果
     */
    List<Manager> findByPage(int pageNo, int pageSz, String mId);

    /**
     * 通过用户名查找
     * @param username 用户名
     * @return 查询结果
     */
    Manager findByUsername(String username);
}
