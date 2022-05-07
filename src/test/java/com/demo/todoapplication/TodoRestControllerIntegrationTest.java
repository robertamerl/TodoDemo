package com.demo.todoapplication;

import com.demo.todoapplication.controllers.TodoController;
import com.demo.todoapplication.domain.Todo;
import com.demo.todoapplication.repositories.TodoRepository;
import com.demo.todoapplication.services.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TodoRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoService todoService;

    @Test
    public void givenTodo_whenGetTodo_thenReturnJsonArray()
            throws Exception {

        Todo todo = new Todo("Title1", "description1");
        List<Todo> allTodos = Arrays.asList(todo);

        given(todoService.getTodos()).willReturn(allTodos);

        mvc.perform(get("/demo/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value(todo.getTitle()));
    }

    // Roberta Merlo: TODO - write negative tests and tests for other endpoints
}
