package com.buildourweb.springbootangularjstodo.dao;

import com.buildourweb.springbootangularjstodo.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
}
