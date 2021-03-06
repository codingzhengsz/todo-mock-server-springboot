package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.common.JsonResult;
import com.thoughtworks.springbootemployee.dto.TodoRequest;
import com.thoughtworks.springbootemployee.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.thoughtworks.springbootemployee.common.JsonResult.success;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public JsonResult getAllTodo() {
        return success(todoService.getAllTodos());
    }

    @PatchMapping("/{id}")
    public JsonResult changeCompleteOfTodo(@PathVariable int id) {
        this.todoService.changeCompleteOfTodoById(id);
        return success();
    }

    @PostMapping
    public JsonResult createNewTodo(@RequestBody TodoRequest todoRequest) {

        return success(this.todoService.createTodo(todoRequest));
    }

    @DeleteMapping("/{id}")
    public JsonResult deleteTodoById(@PathVariable int id) {

        return success(this.todoService.deleteTodoById(id));
    }
}
