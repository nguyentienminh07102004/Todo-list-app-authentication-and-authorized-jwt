package org.PTITB22DCCN539.Service;

import org.PTITB22DCCN539.Model.Request.User.UserDTO;
import org.PTITB22DCCN539.Model.Request.User.UserLoginDTO;
import org.PTITB22DCCN539.Model.Response.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserDTO userDTO);
    String login(UserLoginDTO userLoginDTO);
    UserResponseDTO findById(String id);
}
