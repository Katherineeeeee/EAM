package com.rookie.dao;

import com.rookie.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 访问Property的JPA接口
 */
@Repository
public interface PropertyRepo extends JpaRepository<Property, Integer> {

    /**
     * 通过pId查找
     * @param pId 资产的id
     * @return 查询结果
     */
    Optional<Property> findBypId(Integer pId);

    /**
     * 通过未使用来统计数量
     * @return 数量
     */
    @Query(nativeQuery = true, value = "select count (*) from PropertyTable where user_uId is null")
    long countByUnused();

    /**
     * 通过未使用来分页查找
     * @param pageable 分页类
     * @return 查询结果
     */
    @Query(nativeQuery = true, value = "select * from PropertyTable where user_uId is null",
    countQuery = "select count (*) from PropertyTable where user_uId is null")
    Page<Property> findUnusedByPage(Pageable pageable);

    /**
     * 通过user_uId来统计数量
     * @param user_uId 用户的id
     * @return 数量
     */
    long countByuser_uId(Integer user_uId);

    /**
     * 通过用户的id来分页查找
     * @param pageable 分页类
     * @param user_uId  用户的id
     * @return 查询结果
     */
    Page<Property> findByuser_uId(Pageable pageable, Integer user_uId);
}
