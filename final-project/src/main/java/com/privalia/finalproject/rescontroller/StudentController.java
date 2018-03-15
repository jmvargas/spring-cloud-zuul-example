package com.privalia.finalproject.rescontroller;

import com.privalia.finalproject.domain.Student;
import com.privalia.finalproject.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api")
@Api(value = "student", description = "Operations pertaining to students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @ApiOperation("Search a student with an ID")
    @GetMapping("/student/{id}")
    public Student show(@PathVariable Integer id){
        return studentService.get(id);
    }

    @ApiOperation("List students")
    @GetMapping("/students")
    public Iterable<Student> list(){
        return studentService.list();
    }

    @ApiOperation("Create a new student")
    @PostMapping("address")
    public Student create(@Valid @RequestBody Student student){
        return studentService.save(student);
    }

    @ApiOperation("Update a student")
    @PutMapping("/student/{id}")
    public Student update(@PathVariable Integer id, @Valid @RequestBody Student student){
        return studentService.update(id, student);
    }

    @ApiOperation("Delete a student")
    @DeleteMapping("/student/{id}")
    public void delete(@PathVariable Integer id){
        studentService.delete(id);
    }
}
