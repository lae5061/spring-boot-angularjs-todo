package com.buildourweb.springbootangularjstodo.service;

import com.buildourweb.springbootangularjstodo.controller.TodoController;
import com.buildourweb.springbootangularjstodo.dao.TodoRepository;
import com.buildourweb.springbootangularjstodo.model.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class TodoService {

    private static Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoRepository todoRepository;

    @PostConstruct
    public void initializeDatabase() {
        todoRepository.save(new Todo("Buy Water", "Luc Eleazar", LocalDate.of(2019, 04, 24)));
        todoRepository.save(new Todo("Buy Bread", "Roberto Romero", LocalDate.of(2019, 04, 14)));
        todoRepository.save(new Todo("Buy Milk", "John Doe", LocalDate.of(2019, 04, 04)));
        logger.info("3 new Todos save in db");
    }

    /**
     * Save or Update Todo (Create Update)
     *
     * @param todo from front to save in DB
     * @return
     */
    public Todo save(Todo todo) {
        logger.info("save " + todo);
        return this.todoRepository.save(todo);
    }

    /**
     * Return all Todo in the database (Read)
     *
     * @return
     */
    public Iterable<Todo> findAll() {
        logger.info("findAll");
        return this.todoRepository.findAll();
    }

    /**
     * Delete the todo whose id matches (Delete)
     *
     * @param id
     */
    public void deleteById(Long id) {
        logger.info("id: " + id);
        if (id != null) {
            try {
                this.todoRepository.deleteById(id);
                return;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        throw new IllegalStateException("Invalid Id: " + id);
    }

    /**
     * Id of record that you want to retrieve
     *
     * @param id record id
     */
    public Todo findById(Long id) {

        if (id != null) {
            Optional<Todo> optionalTodo = this.todoRepository.findById(id);
            if (optionalTodo.isPresent()) {
                return optionalTodo.get();
            }
        }
        throw new IllegalStateException("Invalid Id: " + id);
    }
}
