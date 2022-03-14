package com.todoweb.todoSpringApp.service;

import com.todoweb.todoSpringApp.model.TodoEntity;
import com.todoweb.todoSpringApp.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public String testService() {
        //TodoEntity 생성
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        // TodoEntity 저장
        repository.save(entity);
        //TodoEntity 검색
        TodoEntity savedEntity = repository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity) {
        validate(entity);
        repository.save(entity);
        return repository.findByUserId(entity.getUserId());
    }

    public List<TodoEntity> findAll() {
        return repository.findAll();
    }

    public List<TodoEntity> findByUserId(final String userId) {
        return repository.findByUserId(userId);
    }

    public List<TodoEntity> update(final TodoEntity entity) {
        validate(entity);
        final Optional<TodoEntity> original = repository.findById(entity.getId());
        //Optional ㅅㅂ
        //get은 entity만
        original.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            repository.save(todo);
        });

        return findByUserId(entity.getUserId());
    }

    public void delete() {

    }

    public List<TodoEntity> deleteById(final TodoEntity entity) {
        validate(entity);

        try {
            repository.delete(entity);
        } catch (Exception e) {
            log.error("error deleting entity ", entity.getId(), e);
            throw new RuntimeException("error deleting entity " + entity.getId());
        }
        return findByUserId(entity.getUserId());
    }

    private void validate(final TodoEntity entity) {
        if(entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if(entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
}
