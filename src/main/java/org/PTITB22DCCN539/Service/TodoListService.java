package org.PTITB22DCCN539.Service;

import org.PTITB22DCCN539.Model.Request.Todolist.TodoListDTO;
import org.PTITB22DCCN539.Model.Request.Todolist.TodoListRequest;
import org.PTITB22DCCN539.Model.Response.TodoListResponse;

import java.util.List;

public interface TodoListService {
    List<TodoListResponse> findAll(TodoListRequest request);
    TodoListResponse save(TodoListDTO todoListDTO);
}
