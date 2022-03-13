package com.todoweb.todoSpringApp.controller;

import com.todoweb.todoSpringApp.dto.ResponseDTO;
import com.todoweb.todoSpringApp.dto.TestRequestBodyDTO;
import com.todoweb.todoSpringApp.dto.TodoDTO;
import com.todoweb.todoSpringApp.model.TodoEntity;
import com.todoweb.todoSpringApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TodoService service;

    @GetMapping
    public String testController() {
        return "Hello World !";
    }

    @GetMapping("/testGetMapping")
    public String testControllerWithPath() {
        return "Hello World! testGetMapping !!";
    }

    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
        return "Hello world ! ID :" + id;
    }

    @GetMapping("/testRequestParam")
    public String testControllerRequestParam(@RequestParam(required = false) int id) {
        return "Hello world ! ID " + id;
    }

    @GetMapping("/testRequestBody")
    public String testControllerRequestBody (@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
        return "Hello World ! ID : " + testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
    }

    @GetMapping("/testResponseBody")
    public ResponseDTO<String> testControllerResponseBody() {
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm ResponseDTO");
        list.add("hello hello");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return response;
    }

    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity() {
        List<String> list = new ArrayList<>();
        list.add("Hello World ! I'm ResponseEntity.");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/todo")
    public ResponseEntity<?> testTodo() {
        String str = service.testService();
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }
}