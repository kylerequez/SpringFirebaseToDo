package com.github.kylerequez.SpringFirebaseToDo.Todo;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface TodoService {
    public ResponseEntity<List<Todo>> getAllTodoDetails() throws InterruptedException, ExecutionException;
    public ResponseEntity<Todo> getTodoDetailsByName(String title) throws InterruptedException, ExecutionException;
    public ResponseEntity<Todo> saveTodoDetails(Todo todo) throws InterruptedException, ExecutionException;
    public ResponseEntity<Todo> updateTodoDetails(String title, Todo todo) throws InterruptedException, ExecutionException;
}
