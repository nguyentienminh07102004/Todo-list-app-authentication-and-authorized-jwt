package org.PTITB22DCCN539.Model.Request.Todolist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TodoListDTO {
    private String id;
    private String name;
    private String description;
    private Date startDate;
    private Date compiledDate;
}
