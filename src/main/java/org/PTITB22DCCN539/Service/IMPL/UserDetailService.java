package org.PTITB22DCCN539.Service.IMPL;

import org.PTITB22DCCN539.Exception.MyException.MyException;
import org.PTITB22DCCN539.Model.Entity.UserEntity;
import org.PTITB22DCCN539.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new MyException("Không tìm thấy user có email = " + username));
        return User.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .build();
    }
}
