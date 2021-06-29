package com.rookie.dao;

import com.rookie.model.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

/**
 * 访问Application的JPA接口
 */
@Repository
public interface ApplicationRepo extends JpaRepository<Application, Integer> {

    /**
     * 通过aStatus来统计数量
     * @param aStatus
     * @return 数量
     */
    long countByaStatusEquals(Integer aStatus);

    /**
     * 通过user_uId来统计数量
     * @param user_uId
     * @return 数量
     */
    long countByuser_uId(Integer user_uId);

    /**
     * 通过用户和Status来进行分页查询
     * @param user_uId 用户的id
     * @param aStatus aStatus
     * @param pageable 分页类
     * @return 查询结果
     */
    @Query(nativeQuery = true, value = "select * from ApplicationTable where user_uId = ?1 and aStatus = ?2",
    countQuery = "select count (*) from ApplicationTable where user_uId = ?1 and aStatus = ?2")
    Page<Application> findByUserAndaStatus(Integer user_uId, Integer aStatus, Pageable pageable);

    /**
     * 通过用户的id来进行分页查询
     * @param pageable 分页类
     * @param user_uId 用户的id
     * @return 查询结果
     */
    Page<Application> findByuser_uId(Pageable pageable, Integer user_uId);

    /**
     * 通过user_uId和aStatus删除相关的申请单
     * @param user_uId 用户的id
     * @return
     */
    @Query(nativeQuery = true, value = "select * from ApplicationTable where user_uId = ?1 and aStatus = ?2")
    List<Application> findByuser_uIdAndAStatus(Integer user_uId, Integer aStatus);


    /**
     * 通过property_pId来统计数量
     * @param property_pId 资产id
     * @return 数量
     */
    long countByproperty_pId(Integer property_pId);

    /**
     * 通过property_pId来进行分页查询
     * @param pageable 分页类
     * @param property_pId 资产的id
     * @return 查询结果
     */
    Page<Application> findByproperty_pId(Pageable pageable, Integer property_pId);

    /**
     * 通过申请单id来查询
     * @param aId 申请单id
     * @return 查询结果
     */
    Optional<Application> findByaId(Integer aId);

    /**
     * 通过id和aStatus和operation来查询
     * @param property_pId 资产id
     * @param aStatus aStatus
     * @param operation operation
     * @return 查询结果
     */
    @Query(nativeQuery = true, value = "select * from ApplicationTable where property_pId = ?1 and aStatus = ?2 and operation = ?3")
    List<Application> findByproperty_pIdAndAStatusAndOperation(Integer property_pId, Integer aStatus, Integer operation);

    /**
     * 查找所有未检查的
     * @param pageable 分页类
     * @return 查询结果
     */
    @Query(nativeQuery = true, value = "select * from ApplicationTable where aStatus = 0",
    countQuery = "select count(*) from ApplicationTable where aStatus = 0")
    Page<Application> findAllUnChecked(Pageable pageable);

    /**
     * 通过用户id和资产id来查询
     * @param user_uId 用户id
     * @param property_pId 资产id
     * @return 查询结果
     */
    @Query(nativeQuery = true, value = "select * from ApplicationTable where user_uId = ?1 and property_pId = ?2 and aStatus = 0")
    Optional<Application> findByuser_uIdAndproperty_pId(Integer user_uId, Integer property_pId);

}
