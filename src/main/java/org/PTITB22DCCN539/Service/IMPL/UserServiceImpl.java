package org.PTITB22DCCN539.Service.IMPL;

import org.PTITB22DCCN539.Convertor.UserConvertor;
import org.PTITB22DCCN539.Exception.MyException.DataInvalidException;
import org.PTITB22DCCN539.Exception.MyException.MyUnauthorizedException;
import org.PTITB22DCCN539.Model.Entity.UserEntity;
import org.PTITB22DCCN539.Model.Request.User.UserDTO;
import org.PTITB22DCCN539.Model.Request.User.UserLoginDTO;
import org.PTITB22DCCN539.Model.Response.UserResponseDTO;
import org.PTITB22DCCN539.Repository.UserRepository;
import org.PTITB22DCCN539.Sercurity.JWTGenToken;
import org.PTITB22DCCN539.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConvertor userConvertor;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTGenToken jwtGenToken;

    @Override
    public UserResponseDTO createUser(UserDTO userDTO) {
        if(userDTO == null) {
            throw new DataInvalidException();
        }
        if(userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            throw new DataInvalidException("Email is not null !!");
        }
        if(userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new DataInvalidException("Password is not null !!!");
        }
        if(userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DataInvalidException("Email " + userDTO.getEmail() + " is exists !");
        }
        UserEntity user = userConvertor.dtoToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserEntity response = userRepository.save(user);
        return userConvertor.entityToResponseDTO(response);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();
        if(email == null || email.isBlank() || password == null || password.isBlank()) {
            throw new DataInvalidException("Username or password is invalid");
        }
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(()  -> new DataInvalidException("Username or password is invalid"));
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Username or password is invalid");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return jwtGenToken.generateToken(user);
    }

    @Override
    public UserResponseDTO findById(String id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new DataInvalidException("Id = " + id + " is not exists !"));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!userDetails.getUsername().equals(user.getEmail()) && !userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            throw new MyUnauthorizedException();
        }
        return userConvertor.entityToResponseDTO(user);
    }
}
