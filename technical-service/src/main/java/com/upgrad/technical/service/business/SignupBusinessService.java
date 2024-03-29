package com.upgrad.technical.service.business;

import com.upgrad.technical.service.dao.UserDao;
import com.upgrad.technical.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignupBusinessService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

    @Transactional(propagation = Propagation.REQUIRED)
    //This method receives the UserEntity type object and calls createUser() method in UserDao class.
    //This method returns the UserEntity type object which has been stored in a database.
    public UserEntity signup(UserEntity userEntity) {
        String [] encryptedText = cryptographyProvider.encrypt(userEntity.getPassword());
        userEntity.setSalt(encryptedText[0]);
        userEntity.setPassword(encryptedText[1]);
        return userDao.createUser(userEntity);
    }
}
