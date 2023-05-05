package com.github.kylerequez.SpringFirebaseToDo.Todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {
    private String title;
    private String description;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private String status = "on-going";
}
