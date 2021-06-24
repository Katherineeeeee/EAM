package com.rookie.service;

import com.rookie.model.User;

import java.util.List;

/**
 * UserManage的Service接口
 */
public interface UserManageService {

    /**
     * 添加用户
     * @param parse 用户
     * @return 添加是否成功
     */
    boolean addUser(User parse);

    /**
     * 更新用户的状态
     * @param uId 用户的id
     * @param status 状态
     * @return 更新是否成功
     */
    boolean updateUser(Integer uId, Integer status);

    /**
     * 更新用户
     * @param id id
     * @param parse 用户的信息
     * @return 更新是否成功
     */
    boolean updateUser(Integer id, User parse);

    /**
     * 更新用户
     * @param user 用户
     * @return 是否更新成功
     */
    boolean updateUser(User user);

    /**
     * 通过用户id查找用户
     * @param id 用户id
     * @return 查询结果
     */
    User findByuId(Integer id);

    /**
     * 通过id删除用户
     * @param id 用户id
     * @return 删除是否成功
     */
    boolean delUserByuId(Integer id);

    /**
     * 获取页面的数量
     * @param pageSz 页面的最大大小
     * @return 页面的数量
     */
    int getNumOfPage(Integer pageSz);

    /**
     * 通过用户名查找User
     * @param username 用户名
     * @return 查询结果
     */
    User findByUsername(String username);

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSz 页面的数量
     * @return 查询结果
     */
    List<User> findByPage(int pageNo, int pageSz);

    /**
     * 验证用户账号和密码是否匹配
     * @param username 用户名
     * @param password 密码
     * @return 如果用户不存在, 则返回-1, 如果用户存在但是账号与密码不匹配或者用户的状态不正常, 则返回-2, 如果账号和密码匹配, 则返回0
     */
    int verify(String username, String password);
}
