package com.itsol.recruit.service;

import com.itsol.recruit.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUser();

    public User findById(Long id);

    public User findUserByUserName(String userName);

    public boolean isExistedUser(String userName);
    public boolean isExistedUserByEmail(String Email);

    public User findUserByEmail(String email);
    public User findUserByPhone(String phone);

    void sendConfirmUserRegistrationViaEmail(String email);

    void activeAccount(Long id);
}
