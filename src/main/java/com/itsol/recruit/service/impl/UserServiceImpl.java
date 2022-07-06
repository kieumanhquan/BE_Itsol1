package com.itsol.recruit.service.impl;

import com.itsol.recruit.entity.User;
import com.itsol.recruit.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        if (userRepository.findByUserName(userName) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistedUserByEmail(String Email) {
        if (userRepository.findByEmail(Email).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
    @Override
    public void sendConfirmUserRegistrationViaEmail(String email) {
        eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));
    }

    @Override
    public void activeAccount(Long id) {
        User user = userRepository.getById(id);
        user.setActive(true);
        userRepository.save(user);
    }


}
