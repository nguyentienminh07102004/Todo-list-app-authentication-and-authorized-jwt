package org.PTITB22DCCN539.Model.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDTO {
    private String id;
    private String fullName;
    private String dateOfBirth;
    private String email;
    private String status;
    private List<String> roles;
}
