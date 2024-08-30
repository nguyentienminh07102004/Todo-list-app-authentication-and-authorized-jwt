package org.PTITB22DCCN539.Model.Request.User;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String id;
    private String email;
    private String password;
    private List<String> roles;
    private String dateOfBirth;
    private String status;
    private String idNumber;
    private String firstname;
    private String lastname;
}
