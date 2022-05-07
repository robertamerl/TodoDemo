package com.demo.todoapplication.services;

import com.demo.todoapplication.domain.Todo;
import com.demo.todoapplication.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public Todo saveTodo(Todo todo) {
        return repository.save(todo);
    }

    public List<Todo> getTodos() {

        // Here the logic to order the list on the basis of:
        // - state
        // - creation date
        Iterable<Todo> todoNewList = repository.findByStatusOrderByTimestampDesc("NEW");
        Iterable<Todo> todoWorkingList = repository.findByStatusOrderByTimestampDesc("WORKING");
        Iterable<Todo> todoDoneList = repository.findByStatusOrderByTimestampDesc("DONE");

        List<Todo> todoList = new ArrayList<>();
        for (Iterator<Todo> it = todoNewList.iterator(); it.hasNext(); ) {
            Todo todo = it.next();
            todoList.add(todo);
        }
        for (Iterator<Todo> it = todoWorkingList.iterator(); it.hasNext(); ) {
            Todo todo = it.next();
            todoList.add(todo);
        }
        for (Iterator<Todo> it = todoDoneList.iterator(); it.hasNext(); ) {
            Todo todo = it.next();
            todoList.add(todo);
        }
        return todoList;
    }

    public Todo getTodoById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Todo getTodoByTitle(String title) {
        return repository.findByTitle(title).orElse(null);
    }

    public String deleteTodo(int id) {
        repository.deleteById(id);
        return "todo removed !! " + id;
    }

    public Todo updateTodo(String title, String state) {
        Todo existingTodo = repository.findByTitle(title).orElse(null);

        if (existingTodo != null) {
            existingTodo.setStatus(state);
            repository.save(existingTodo);
        } else {
            return null;
        }
        return existingTodo;
    }

}


