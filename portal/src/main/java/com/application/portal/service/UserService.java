package com.application.portal.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import com.application.portal.domain.User;
import com.application.portal.exception.domain.EmailExistException;
import com.application.portal.exception.domain.EmailNotFoundException;
import com.application.portal.exception.domain.UserNotFoundException;
import com.application.portal.exception.domain.UsernameExistException;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException;

    User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException;

    void deleteUser(String username) throws IOException;

    void resetPassword(String email) throws MessagingException, EmailNotFoundException;
    
    void changePassword(String email,String password) throws MessagingException, EmailNotFoundException;


    //User updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;
}
