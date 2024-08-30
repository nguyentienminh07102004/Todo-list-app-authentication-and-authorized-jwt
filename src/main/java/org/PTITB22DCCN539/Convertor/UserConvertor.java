package org.PTITB22DCCN539.Convertor;

import org.PTITB22DCCN539.Model.Entity.RoleEntity;
import org.PTITB22DCCN539.Model.Entity.UserEntity;
import org.PTITB22DCCN539.Model.Request.User.UserDTO;
import org.PTITB22DCCN539.Model.Response.UserResponseDTO;
import org.PTITB22DCCN539.Repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;

    public UserEntity dtoToEntity(UserDTO userDTO) {
        UserEntity user = modelMapper.map(userDTO, UserEntity.class);
        user.setRoles(roleRepository.findAllByCodeIn(userDTO.getRoles()));
        user.setStatus("ACTIVE");
        return user;
    }

    public UserResponseDTO entityToResponseDTO(UserEntity user) {
        UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);
        userResponseDTO.setRoles(user.getRoles().stream()
                .map(RoleEntity::getName)
                .toList());
        return userResponseDTO;
    }
}
