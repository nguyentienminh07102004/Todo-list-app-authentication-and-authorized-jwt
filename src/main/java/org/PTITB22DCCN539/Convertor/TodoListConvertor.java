package org.PTITB22DCCN539.Convertor;

import org.PTITB22DCCN539.Model.Entity.TodoListEntity;
import org.PTITB22DCCN539.Model.Request.Todolist.TodoListDTO;
import org.PTITB22DCCN539.Model.Response.TodoListResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class TodoListConvertor {
    @Autowired
    private ModelMapper modelMapper;

    public TodoListResponse entityToResponse(TodoListEntity todoListEntity) {
        return modelMapper.map(todoListEntity, TodoListResponse.class);
    }

    public TodoListEntity dtoToEntity(TodoListDTO todoListDTO) {
        TodoListEntity entity = modelMapper.map(todoListDTO, TodoListEntity.class);
        entity.setStartDate(todoListDTO.getStartDate() == null ? new Date(System.currentTimeMillis()) : todoListDTO.getStartDate());
        return entity;
    }
}
