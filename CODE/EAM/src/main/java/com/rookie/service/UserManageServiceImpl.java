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
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Resource
    private UserRepo userRepo;

    @Resource
    private ApplicationRepo applicationRepo;

    @Resource
    private PropertyRepo propertyRepo;

    /**
     * 通过用户名查找User
     * @param username 用户名
     * @return 查询结果
     */
    @Override
    public User findByUsername(String username) {
        return userRepo.findByuName(username).orElse(null);
    }

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSz 页面的数量
     * @return 查询结果
     */
    @Override
    public List<User> findByPage(int pageNo, int pageSz) {
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSz, Sort.by(Sort.Direction.ASC, "uId"));
        Page<User> res = userRepo.findAll(pageRequest);
        return res.toList();
    }

    /**
     * 验证用户账号和密码是否匹配
     * @param username 用户名
     * @param password 密码
     * @return 如果用户不存在, 则返回-1, 如果用户存在但是账号与密码不匹配或者用户的状态不正常, 则返回-2, 如果账号和密码匹配, 则返回0
     */
    @Override
    public int verify(String username, String password) {
        User user = findByUsername(username);
        if (user == null) return -1;
        if (!user.getuPassword().equals(password) || user.getuStatus() != 1) {
            return -2;
        }
        return 0;
    }

    /**
     * 获取页面的数量
     * @param pageSz 页面的最大大小
     * @return 页面的数量
     */
    @Override
    public int getNumOfPage(Integer pageSz) {
        int num = (int)userRepo.count();
        return (num + pageSz - 1) / pageSz;
    }

    /**
     * 通过用户id查找用户
     * @param uId 用户id
     * @return 查询结果
     */
    @Override
    public User findByuId(Integer uId) {
        return userRepo.findByuId(uId).orElse(null);
    }

    /**
     * 添加用户
     * @param user 用户
     * @return 添加是否成功
     */
    @Override
    public boolean addUser(User user) {
        try {
            return addUser1(user);
        } catch (Exception e){
            return false;
        }
    }

    public boolean addUser1(@Valid User user) {
        User user2 = userRepo.findByuName(user.getuName()).orElse(null);
        if (user2 == null) {
            user.setuStatus(1);
            userRepo.save(user);
            return true;
        }
        return false;
    }

    /**
     * 更新用户
     * @param user 用户
     * @return 是否更新成功
     */
    @Override
    public boolean updateUser(User user) {
        try {
            updateUser1(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean updateUser1(@Valid User user) {
        userRepo.save(user);
        return true;
    }

    /**
     * 更新用户的状态
     * @param id 用户的id
     * @param status 状态
     * @return 更新是否成功
     */
    @Override
    public boolean updateUser(Integer id, Integer status) {
        User user = findByuId(id);
        if (user == null) return false;
        if (user.getuStatus() == -1)return false;
        if (status.intValue() != 1 && status.intValue() != -1 && status != 0) return false;
        if (status == -1 || status == 0) {
            List<Application> applications = applicationRepo.findByuser_uIdAndAStatus(id, 0);
            applicationRepo.deleteAll(applications);
        }
        if (status == -1) {
            List<Application> applications = applicationRepo.findByuser_uIdAndAStatus(id, 1);
            List<Application> delList = new ArrayList<>();
            List<Property> delList2 = new ArrayList<>();
            for (Application application: applications) {
                if (application.getProperty().getUser().getuId().equals(id)) {
                    delList.add(application);
                    application.getProperty().setUser(null);
                    delList2.add(application.getProperty());
                    application.setEndTime(new Date());
                }
            }
            applicationRepo.saveAll(delList);
            propertyRepo.saveAll(delList2);
        }
        user.setuStatus(status);
        return updateUser(user);
    }

    /**
     * 更新用户
     * @param id id
     * @param parse 用户的信息
     * @return 更新是否成功
     */
    @Override
    public boolean updateUser(Integer id, User parse) {
        User user = findByuId(id);
        if (user == null) {
            return false;
        } else {
            if (parse.getuEmail() != null) {
                user.setuEmail(parse.getuEmail());
            }
            if (parse.getuPassword() != null) {
                user.setuPassword(parse.getuPassword());
            }
            return updateUser(user);
        }
    }

    /**
     * 通过id删除用户
     * @param uId 用户id
     * @return 删除是否成功
     */
    @Override
    public boolean delUserByuId(Integer uId) {
        User user = userRepo.findByuId(uId).orElse(null);
        if (user == null) {
            return false;
        } else {
            List<Application> applications = applicationRepo.findByuser_uIdAndAStatus(uId, 0);
            applicationRepo.deleteAll(applications);
            user.setuStatus(-1);
            userRepo.save(user);
            return true;
        }
    }
}
