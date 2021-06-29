package com.rookie.dao;

import com.rookie.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 访问Application的JPA接口
 */
@Repository
public interface ManagerRepo extends JpaRepository<Manager, Integer> {

    /**
     * 通过名字查找
     * @param mName 管理员名字
     * @return 查询结果
     */
    Optional<Manager> findBymName(String mName);

    /**
     * 通过id查找
     * @param mId 管理员id
     * @return 查询结果
     */
    Optional<Manager> findBymId(Integer mId);
}
