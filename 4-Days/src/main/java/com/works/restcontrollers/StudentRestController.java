package com.works.restcontrollers;

import com.works.props.Student;
import com.works.repositories.StudentRepository;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentRestController {

    final StudentRepository sRepo;
    final CacheManager manager;
    public StudentRestController(StudentRepository sRepo, CacheManager manager) {
        this.sRepo = sRepo;
        this.manager = manager;
    }


    @PostMapping("/studentAdd")
    public Map<String, Object> studentAdd(@RequestBody @Valid Student student) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("obj", sRepo.save(student));
        manager.getCache("studentList").clear();
        return hm;
    }


    @GetMapping("/studentList")
    @Cacheable(value = "studentList")
    public Map<String, Object> studentList() {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("obj", sRepo.findAll());
        return hm;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handler( MethodArgumentNotValidException ex ) {
        Map<String, Object> hm = new LinkedHashMap<>();

        List<Map<String, String>> hmLs = new ArrayList<>();
        List<FieldError> els = ex.getFieldErrors();
        for ( FieldError itm : els ) {
            String filed = itm.getField();
            String message = itm.getDefaultMessage();

            Map<String, String> h = new LinkedHashMap<>();
            h.put("filed", filed);
            h.put("message",message);
            hmLs.add(h);
        }
        hm.put("errors", hmLs);
        return hm;
    }


}
