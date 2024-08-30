package org.PTITB22DCCN539.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "todolist")
@Getter
@Setter
public class TodoListEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;
    @Column(name = "startdate")
    private Date startDate;
    @Column(name = "compileddate")
    private Date compiledDate;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private UserEntity users;
}
