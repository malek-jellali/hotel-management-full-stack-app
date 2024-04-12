package com.backend.hotelmanagement.Security.user;

import com.backend.hotelmanagement.model.User;
import com.backend.hotelmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class HotelUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("user not found "));
        return HotelUserDetails.buildUserDetails(user);
    }
}
