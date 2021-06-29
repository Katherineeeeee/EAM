package com.rookie.dao;

import com.rookie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 访问User的JPA接口
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    /**
     * 根据ID获得用户
     *
     * @param uId 用户ID
     * @return 查询结果
     */
    Optional<User> findByuId(Integer uId);

    /**
     * 通过名字获得用户
     * @param uName 用户的名字
     * @return 查询结果
     */
    Optional<User> findByuName(String uName);
}
