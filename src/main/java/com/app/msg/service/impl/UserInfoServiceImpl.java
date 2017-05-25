package com.app.msg.service.impl;

import com.app.msg.domain.entity.User;
import com.app.msg.repo.UserRepository;
import com.app.msg.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by infear on 2017/5/25.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean login(String name, String password) {
        User user = userRepository.findByName(name);
        if (user == null || !user.getPassword().equals(password)) {
            return false;
        }
        return true;
    }
}
