package com.example.shopapp.service;

import com.example.shopapp.DTO.UserDTO;
import com.example.shopapp.exception.DataNotFoundException;
import com.example.shopapp.models.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws DataNotFoundException, Exception;
    String login(String phoneNumber, String password);
}
