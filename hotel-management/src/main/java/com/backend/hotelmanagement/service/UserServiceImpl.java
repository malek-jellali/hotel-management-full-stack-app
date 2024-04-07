package com.backend.hotelmanagement.service;

import com.backend.hotelmanagement.exceptions.UserAlreadyExistsException;
import com.backend.hotelmanagement.model.Role;
import com.backend.hotelmanagement.model.User;
import com.backend.hotelmanagement.repository.RoleRepository;
import com.backend.hotelmanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl() {
        userRepository = null;
        roleRepository = null;
        passwordEncoder = null;
    }

    @Override
    public User registerUser(User user) {
        if(userRepository.existByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail() + "already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return null;
    }


    @Transactional
    @Override
    public void deleteUser(String email) {
        User theUser = getUser(email);
        if(theUser !=null){
            userRepository.deleteByEmail(email);
        }
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
