package com.example.shopapp.service;

import com.example.shopapp.DTO.UserDTO;
import com.example.shopapp.exception.DataNotFoundException;
import com.example.shopapp.models.Role;
import com.example.shopapp.models.User;
import com.example.shopapp.repositores.RoleRepository;
import com.example.shopapp.repositores.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        String phoneNumber  = userDTO.getPhoneNumber();
        // kiem tra xem so dien thoai da ton tai chua
        if (userRepository.existsByPhoneNumber(phoneNumber)){
            throw new DataAccessResourceFailureException("Phone number already exist");
        }

        // convert userDTO -> user
        User newUser = User.builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(userDTO.getPassword())
                .address(userDTO.getAddress())
                .dateOfBirth(userDTO.getDateOfBirth())
                .facebookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .build();

        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException("Role nor found"));
        newUser.setRole(role);

        // Kiem tra neu co accountId, khong yeu cau password
//        if (userDTO.getFacebookAccountId()==0 && userDTO.getGoogleAccountId()==0){
//            String password = userDTO.getPassword();
//            String encodedPassword = pass
//        }
        return userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) {
        return null;
    }
}
