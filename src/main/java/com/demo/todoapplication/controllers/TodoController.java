package com.demo.todoapplication.controllers;

import com.demo.todoapplication.domain.Todo;
import com.demo.todoapplication.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.todoapplication.repositories.TodoRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping(path="/demo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity<String> addNewTodo (@RequestParam String title
            , @RequestParam (required = false) String description) {

        Todo todo = new Todo();

        if (title == null || title.isEmpty()) {
            return ResponseEntity.badRequest().body("Title null or empty");
        }
        todo.setTitle(title);

        if (description != null && !description.isEmpty()) {
            todo.setDescription(description);
        }

        todo.setStatus("NEW");

        LocalDateTime dateTime = LocalDateTime.now();
        String dateTimeStr = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime timeStamp = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        todo.setTimestamp(timeStamp);
        todoService.saveTodo(todo);
        return ResponseEntity.ok("Saved");
    }

    @GetMapping(path="/all")
    public @ResponseBody List<Todo> getAllTodos() {

        return todoService.getTodos();
    }

    @PostMapping(path="/update")
    public @ResponseBody ResponseEntity<String> updateTodoState (@RequestParam String title, @RequestParam String state) { // TODO: change to an enum

        if (title == null || title.isEmpty()) {
            return ResponseEntity.badRequest().body("Title null or empty");
        }

        if (state == null || state.isEmpty()) {
            return ResponseEntity.badRequest().body("State null or empty");
        }

        if (!state.equalsIgnoreCase("NEW") && !state.equalsIgnoreCase("WORKING") &&
                !state.equalsIgnoreCase("DONE")) {
            return ResponseEntity.badRequest().body("Unexpected state <" + state + ">");
        }

        String upperCaseState = state.toUpperCase(Locale.ROOT);
        Todo todo = todoService.updateTodo(title, upperCaseState);
        if (todo == null) {
            return ResponseEntity.ok("Todo <" + title + "> not found");
        }

        return ResponseEntity.ok("Updated");
    }

    @GetMapping(path="/get")
    public @ResponseBody Todo getTodoByTitle(@RequestParam String title) {

        Todo todo = new Todo();

        if (title != null && !title.isEmpty()) {
            todo = todoService.getTodoByTitle(title);
        }
        return todo;
    }

}
