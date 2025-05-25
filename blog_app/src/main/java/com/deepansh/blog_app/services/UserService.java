package com.deepansh.blog_app.services;

import com.deepansh.blog_app.exceptions.ResourceNotFoundException;
import com.deepansh.blog_app.payloads.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user,Integer userId) throws ResourceNotFoundException;

    UserDTO getUserById(Integer userId) throws ResourceNotFoundException;

    List<UserDTO> getAllUsers();

    void deleteUser(Integer userId) throws ResourceNotFoundException;

}
