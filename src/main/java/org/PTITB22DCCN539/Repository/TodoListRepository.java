package org.PTITB22DCCN539.Repository;

import org.PTITB22DCCN539.Model.Entity.TodoListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.sql.Date;
import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoListEntity, String> {

    @Query(
            value = "SELECT todo FROM TodoListEntity AS todo WHERE (todo.id = :id OR :id IS NULL) AND (todo.name LIKE %:name% OR :name IS NULL) "
            + " AND (todo.startDate = :startDate OR :startDate IS NULL) AND (todo.compiledDate = :compiledDate OR :compiledDate IS NULL)")
    List<TodoListEntity> findAll(@Nullable String id, @Nullable @Param(value = "name") String name,
                                 @Nullable Date startDate, @Nullable Date compiledDate);
}
