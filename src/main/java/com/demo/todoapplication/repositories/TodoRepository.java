package com.demo.todoapplication.repositories;

import org.springframework.data.repository.CrudRepository;
import com.demo.todoapplication.domain.Todo;

import java.util.Optional;

public interface TodoRepository extends CrudRepository<Todo, Integer>{

    public Optional<Todo> findByTitle(String title);
    public Iterable<Todo> findByStatusOrderByTimestampDesc(String state);
}
