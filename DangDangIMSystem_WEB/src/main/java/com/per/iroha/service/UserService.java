package com.per.iroha.service;

import com.per.iroha.model.User;

public interface UserService {

    void register(User user);

    boolean md5Password(User user,int salt);

    User findByName(String username);

    User findById(int userId);

    boolean hasUser(String username);

    void checkIn(int userId);

    long getTable(int userId,int month);

}
