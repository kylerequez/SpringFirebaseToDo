package com.github.kylerequez.SpringFirebaseToDo.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/todo/api/v1")
@CrossOrigin
public class TodoController {
    @Autowired
    private final TodoServiceImpl todoService;

    public TodoController(TodoServiceImpl todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() throws ExecutionException, InterruptedException {
        return this.todoService.getAllTodoDetails();
    }

    @GetMapping("/{title}")
    public ResponseEntity<Todo> getTodoDetailsByName(@PathVariable String title) throws ExecutionException, InterruptedException {
        return this.todoService.getTodoDetailsByName(title);
    }

    @PostMapping
    public ResponseEntity<Todo> saveTodoDetails(@RequestBody Todo todo) throws ExecutionException, InterruptedException {
        return this.todoService.saveTodoDetails(todo);
    }

    @PatchMapping("/{title}")
    public ResponseEntity<Todo> updateTodoDetails(@PathVariable String title, @RequestBody Todo todo) throws ExecutionException, InterruptedException {
        return this.todoService.updateTodoDetails(title, todo);
    }


}
