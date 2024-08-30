package org.PTITB22DCCN539.API;

import org.PTITB22DCCN539.Model.Request.User.UserDTO;
import org.PTITB22DCCN539.Model.Request.User.UserLoginDTO;
import org.PTITB22DCCN539.Model.Response.UserResponseDTO;
import org.PTITB22DCCN539.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserAPI {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public UserResponseDTO register(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @GetMapping(value = "/{id}")
    public UserResponseDTO findById(@PathVariable(value = "id") String id) {
        return userService.findById(id);
    }
}
