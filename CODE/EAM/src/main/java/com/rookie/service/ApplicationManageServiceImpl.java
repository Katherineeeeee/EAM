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
        int sum = (int) applicationRepo.countByuser_uId(user.getuId());
        return (sum + pageSz - 1) / pageSz;
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
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSz, Sort.by(Sort.Direction.ASC, colName));
        Page<Application> res = applicationRepo.findByuser_uId(pageRequest,user.getuId());
        return res.toList();
    }

    /**
     * 通过页面大小和资产来获取页面的数量
     * @param pageSz 页面大小
     * @param property 资产
     * @return 数量
     */
    @Override
    public int getNumOfPageByPageAndProperty(int pageSz, Property property) {
        int sum = (int) applicationRepo.countByproperty_pId(property.getpId());
        return (sum + pageSz - 1) / pageSz;
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
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSz, Sort.by(Sort.Direction.ASC, colName));
        Page<Application> res = applicationRepo.findByproperty_pId(pageRequest,property.getpId());
        return res.toList();
    }

    /**
     * 获取页面的数量
     * @param pageSz 页面大小
     * @return 数量
     */
    @Override
    public int getNumOfPage(int pageSz) {
        int sum = (int) applicationRepo.countByaStatusEquals(0);
        return (sum + pageSz - 1) / pageSz;
    }

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSz 页面的大小
     * @return 查询结果
     */
    @Override
    public List<Application> findByPage(int pageNo, int pageSz) {
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSz, Sort.by(Sort.Direction.DESC, "beginTime"));
        Page<Application> res = applicationRepo.findAllUnChecked(pageRequest);
        return res.toList();
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
        Application application = applicationRepo.findByaId(id).orElse(null);
        if (application == null) return false;        System.out.println("status: " + status + "\n" +
                "aStatus: " + application.getaStatus() + "\n" +
                "aId: " + application.getaId() + "\n" +
                "operation: " + application.getOperation() + "\n");

        if (application.getaStatus() == 0 && status == 1) {//审核中,并且管理员是通过操作
            application.setReviewTime(new Date());
            application.setManager(manager);
            application.setaStatus(status);
            applicationRepo.save(application);
            if (application.getOperation() == 1 && application.getProperty().getUser() == null) {
                application.getProperty().setUser(application.getUser());
                propertyRepo.save(application.getProperty());
                System.out.println("!!!!!!!!!!!!!!!11");
                List<Application> autoRefused = applicationRepo.findByproperty_pIdAndAStatusAndOperation(application.getProperty().getpId(), 0, 1);
                System.out.println("2222222222222");
                autoRefused = new ArrayList<>(autoRefused);
                for (Application application1: autoRefused) {
                    application1.setaStatus(-1);
                    Date curDate = new Date();
                    application1.setReviewTime(curDate);
                    application1.setEndTime(curDate);
                    application1.setManager(manager);
                }
                applicationRepo.saveAll((ArrayList)autoRefused);
                return true;
            }
            if (application.getOperation() == 0 && application.getProperty().getUser().getuId().equals(application.getUser().getuId())) {
                application.getProperty().setUser(null);
                propertyRepo.save(application.getProperty());
                return true;
            }
        }
        if (application.getaStatus() == 0 && status == -1) {//审核中并且管理员是拒绝操作
            Date curDate = new Date();
            application.setReviewTime(curDate);
            application.setEndTime(curDate);
            application.setManager(manager);
            application.setaStatus(status);
            applicationRepo.save(application);
            return true;
        }
        return false;
    }
}
