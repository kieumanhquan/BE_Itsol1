package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.Role;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.RoleRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl  implements UserService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public final UserRepository userRepository;
    public final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override

    public boolean isExistedUser(String userName) {
        return false;
    }

    @Override
    public boolean isExistedUserByEmail(String Email) {
        return false;
    }

    @Override
    public void sendConfirmUserRegistrationViaEmail(String email) {

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByPhone(String phone) {
        return userRepository.findUserByPhoneNumber(phone);
    }

    @Override
    public void activeAccount(Long id) {

        User user = userRepository.getById(id);
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Set<Role> findByCode(String code) {
        return roleRepository.findByCode(code);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getJE() {
        return userRepository.getJE();
    }




}