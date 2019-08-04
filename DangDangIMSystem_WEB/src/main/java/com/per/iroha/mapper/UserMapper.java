package com.per.iroha.mapper;

import com.per.iroha.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findByUsername(String username);

    User findByUserId(int userId);

    void userRegister(User user);
}
