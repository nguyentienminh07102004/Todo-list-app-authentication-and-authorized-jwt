package org.PTITB22DCCN539.Service.IMPL;

import org.PTITB22DCCN539.Convertor.TodoListConvertor;
import org.PTITB22DCCN539.Model.Entity.TodoListEntity;
import org.PTITB22DCCN539.Model.Request.Todolist.TodoListDTO;
import org.PTITB22DCCN539.Model.Request.Todolist.TodoListRequest;
import org.PTITB22DCCN539.Model.Response.TodoListResponse;
import org.PTITB22DCCN539.Repository.TodoListRepository;
import org.PTITB22DCCN539.Service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

@Service
public class TodoListServiceImpl implements TodoListService {
    @Autowired
    private TodoListRepository todoListRepository;
    @Autowired
    private TodoListConvertor todoListConvertor;

    @Override
    public List<TodoListResponse> findAll(TodoListRequest request) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            Date compiledDate = null;
            String id = null;
            String name = null;
            if(request != null) {
                if(request.getStartDate() != null) {
                    startDate = new Date(format.parse(request.getStartDate()).getTime());
                }
                if(request.getCompiledDate() != null)
                    compiledDate = new Date(format.parse(request.getCompiledDate()).getTime());
                id = request.getId();
                name = request.getName();
            }
            List<TodoListEntity> list = todoListRepository.findAll(id, name,
                    startDate, compiledDate);
            return list.stream().map(item -> todoListConvertor.entityToResponse(item)).toList();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public TodoListResponse save(TodoListDTO todoListDTO) {
        TodoListEntity todoListEntity = todoListConvertor.dtoToEntity(todoListDTO);
        TodoListEntity entity = todoListRepository.save(todoListEntity);
        return todoListConvertor.entityToResponse(entity);
    }
}
