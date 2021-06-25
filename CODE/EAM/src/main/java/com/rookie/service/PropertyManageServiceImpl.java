package com.rookie.service;

import com.rookie.dao.PropertyRepo;
import com.rookie.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Service
public class PropertyManageServiceImpl implements PropertyManageService {

    @Resource
    PropertyRepo propertyRepo;

    /**
     * 获取页面数量
     * @param pageSz 页面的最大大小
     * @return 页面数量
     */
    @Override
    public int getNumOfPage(Integer pageSz) {
        int sz = (int)propertyRepo.count();
        return (sz + pageSz - 1) / pageSz;
    }

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSz 页面大小
     * @return 查询结果
     */
    @Override
    public List<Property> findByPage(int pageNo, int pageSz) {
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSz, Sort.by(Sort.Direction.ASC, "pId"));
        Page<Property> res = propertyRepo.findAll(pageRequest);
        return res.toList();
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

    public boolean addProperty1(@Valid Property property) {
        //Todo
    }

    /**
     * 通过id查找资产
     * @param pId
     * @return
     */
    @Override
    public Property findBypId(Integer pId) {
        return propertyRepo.findBypId(pId).orElse(null);
    }

}
