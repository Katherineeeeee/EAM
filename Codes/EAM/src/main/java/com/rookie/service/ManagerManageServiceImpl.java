package com.rookie.service;

import com.rookie.dao.ManagerRepo;
import com.rookie.model.Manager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Service
public class ManagerManageServiceImpl implements ManagerManageService {

    @Resource
    ManagerRepo managerRepo;

    /**
     * 验证管理员账号和密码是否匹配
     * @param username 用户名
     * @param password 密码
     * @return 如果用户不存在, 则返回-1, 如果用户存在但是账号与密码不匹配或者用户的状态不正常, 则返回-2, 如果账号和密码匹配, 则返回0
     */
    @Override
    public int verify(String username, String password) {
        //Todo
    }

    /**
     * 添加manager
     * @param manager 管理员信息
     * @return
     */
    @Override
    public boolean addManager(Manager manager) {
        //Todo
    }

    /**
     * 添加manager
     * @param manager 管理员信息
     * @return
     */
    public boolean addManager1(Manager manager) {
        //Todo
    }

    /**
     * 分页查找的相关实现方法, 注意PageRequest中传入的参数页号是从0开始计数的(但是我们的前台的页面是从1开始计数的, 因此需要减一)
     * Sort提供了排序的方法, 下面的分页查找可以理解成先对数据按照colName指定的列升序排序(ASC, 降序排序是DESC), 然后再去pageNo - 1和pageSz相关的数据
     * @param pageNo 页号
     * @param pageSz 页面的最大大小
     * @param colName 列名
     * @return 查找的结果
     */
    @Override
    public List<Manager> findByPage(int pageNo, int pageSz, String colName) {
        //Todo
    }

    /**
     * 通过用户名查找
     * @param mName 用户名
     * @return 查询结果
     */
    @Override
    public Manager findByUsername(String mName) {
        return managerRepo.findBymName(mName).orElse(null);
    }

    /**
     * 计算页面的数量的方法(会向上取整)
     * @param pageSz 页面的最大大小
     * @return 页面数量
     */
    @Override
    public int getNumOfPage(int pageSz) {
        //Todo
    }

    /**
     * 更新manager自己
     * @param manager manager
     * @return 是否更新成功
     */
    @Override
    public boolean updateSelf(Manager manager) {
        return save1(manager);
    }

    public boolean save1(@Valid Manager manager) {
        //Todo
    }

    /**
     * 通过id删除manager
     * @param id manager的id
     * @return 是否更新成功
     */
    @Override
    public boolean delManagerById(int id) {
        //Todo
    }

    /**
     * 更新manager的状态
     * @param mId manager的id
     * @param status 新的status
     * @return 是否更新成功
     */
    @Override
    public boolean updateManager(Integer mId, Integer status) {
        //Todo
    }

    public boolean updateManager(Manager manager) {
        //Todo
    }

    public boolean updateManager1(@Valid Manager manager) {
        //Todo
    }
}
