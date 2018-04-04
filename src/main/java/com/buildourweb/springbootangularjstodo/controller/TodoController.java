package com.buildourweb.springbootangularjstodo.controller;

import com.buildourweb.springbootangularjstodo.model.Message;
import com.buildourweb.springbootangularjstodo.model.Todo;
import com.buildourweb.springbootangularjstodo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TodoController {

    private static Logger logger = LoggerFactory.getLogger(TodoController.class);


    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public Iterable<Todo> read(){
        logger.info("read");
       return todoService.findAll();
    }

    @PostMapping("/todos")
    public Todo createOrUpdate(@Valid @RequestBody  Todo todo){
        logger.info("createOrUpdate "+todo);
        return todoService.save(todo);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id){
        logger.info("delete "+id);
        todoService.deleteById(id);
        return new ResponseEntity<>(Message.NO_ERROR_MESSAGE,HttpStatus.OK);
    }

    @PutMapping("/todos/{id}/{completed}")
    public ResponseEntity<Message> setCompleted(@PathVariable Long id, @PathVariable boolean completed){
        logger.info("setCompleted "+id);
        final Todo todo = todoService.findById(id);
        todo.setCompleted(completed);
        todoService.save(todo);
        return new ResponseEntity<>(Message.NO_ERROR_MESSAGE,HttpStatus.OK);
    }
}
