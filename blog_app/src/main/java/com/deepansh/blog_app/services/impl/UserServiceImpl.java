package com.deepansh.blog_app.services.impl;

import com.deepansh.blog_app.entities.User;
import com.deepansh.blog_app.exceptions.ResourceNotFoundException;
import com.deepansh.blog_app.payloads.UserDTO;
import com.deepansh.blog_app.repositories.UserRepo;
import com.deepansh.blog_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = this.dtoToUser(userDto);
        User saved = this.userRepo.save(user);
        return this.userToDTO(saved);
    }

//    @SneakyThrows
    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) throws ResourceNotFoundException {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));

        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setAbout(userDTO.getAbout());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        User updatedUser = this.userRepo.save(user);
        return this.userToDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) throws ResourceNotFoundException {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        return this.userToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        return users.stream().map(user -> this.userToDTO(user)).toList();
    }

    @Override
    public void deleteUser(Integer userId) throws ResourceNotFoundException {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setAbout(userDTO.getAbout());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    private UserDTO userToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setAbout(user.getAbout());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
