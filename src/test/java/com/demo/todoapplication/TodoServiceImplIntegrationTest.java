package com.demo.todoapplication;

import com.demo.todoapplication.domain.Todo;
import com.demo.todoapplication.repositories.TodoRepository;
import com.demo.todoapplication.services.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TodoServiceImplIntegrationTest {

    @Autowired
    private TodoService todoService;

    @MockBean
    private TodoRepository todoRepository;

    @BeforeEach
    public void setUp() {
        Todo todo = new Todo();
        todo.setTitle("Title1");

        Mockito.when(todoRepository.findByTitle(todo.getTitle())).thenReturn(Optional.of(todo));
    }

    @Test
    public void whenValidTitle_thenTodoShouldBeFound() {
        String title = "Title1";
        Todo found = todoService.getTodoByTitle(title);

        assertThat(found.getTitle()).isEqualTo(title);
    }

    // Roberta Merlo: TODO - write other service methods test cases
}
