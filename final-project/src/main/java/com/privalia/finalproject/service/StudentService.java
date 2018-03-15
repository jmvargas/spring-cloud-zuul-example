package com.privalia.finalproject.service;

import com.privalia.finalproject.domain.Address;
import com.privalia.finalproject.domain.Student;
import com.privalia.finalproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressService addressService;

    public Iterable<Student> list() {
        return studentRepository.findAll();
    }

    public Student get(Integer id) {
        return studentRepository.findOne(id);
    }

    @Transactional
    public Student save(Student student) {
        List<Address> target = new ArrayList<>();
        addressService.save(student.getAddress()).forEach(target::add);
        student.setAddress(target);
        return studentRepository.save(student);
    }

    @Transactional
    public void delete(Integer id) {
        Student student = this.get(id);
        addressService.delete(student.getAddress());
        studentRepository.delete(id);
    }

    @Transactional
    public Student update(Integer id, Student student){
        Student old = this.get(id);
        old.setName(student.getName());

        List<Address> target = new ArrayList<>();
        addressService.save(student.getAddress()).forEach(target::add);
        old.setAddress(target);
        return this.save(old);
    }
}
