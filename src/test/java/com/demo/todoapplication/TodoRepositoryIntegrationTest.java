package com.demo.todoapplication;

import com.demo.todoapplication.domain.Todo;
import com.demo.todoapplication.repositories.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void whenFindByTitle_thenReturnTodo() {
        Todo todo = new Todo("Title", "First activity");
        entityManager.persist(todo);
        entityManager.flush();

        Optional<Todo> foundOpt = todoRepository.findByTitle(todo.getTitle());
        Todo found;
        if (foundOpt.isPresent()) {
            found = foundOpt.get();
            assertThat(found.getTitle()).isEqualTo(todo.getTitle());
        }
    }

    // Roberta Merlo: TODO - write negative tests for findByTitle
    //                       and write tests for findByStatusOrderByTimestampDesc

}
