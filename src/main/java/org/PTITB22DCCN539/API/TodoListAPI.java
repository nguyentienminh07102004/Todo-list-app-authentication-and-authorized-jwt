package org.PTITB22DCCN539.API;

import org.PTITB22DCCN539.Model.Request.Todolist.TodoListDTO;
import org.PTITB22DCCN539.Model.Request.Todolist.TodoListRequest;
import org.PTITB22DCCN539.Model.Response.TodoListResponse;
import org.PTITB22DCCN539.Service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/todolist")
public class TodoListAPI {

    @Autowired
    private TodoListService todoListService;

    @GetMapping(value = "/")
    @PreAuthorize(value = "hasRole('MANAGER')")
    public List<TodoListResponse> findAll(@RequestParam(required = false) TodoListRequest request) {
        return todoListService.findAll(request);
    }

    @PostMapping(value = "/")
    public TodoListResponse save(@RequestBody TodoListDTO todoListDTO) {
        return todoListService.save(todoListDTO);
    }
}
