package org.PTITB22DCCN539.Model.Request.Todolist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoListRequest {
    private String id;
    private String name;
    private String description;
    private String startDate;
    private String compiledDate;
}
