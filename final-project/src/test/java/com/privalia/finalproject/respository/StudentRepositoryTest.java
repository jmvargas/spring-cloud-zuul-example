package com.privalia.finalproject.respository;

import com.privalia.finalproject.config.RepositoryConfiguration;
import com.privalia.finalproject.domain.Address;
import com.privalia.finalproject.domain.Student;
import com.privalia.finalproject.repository.AddressRepository;
import com.privalia.finalproject.repository.StudentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AddressRepository addressRepository;
    private Student student1 = null;

    @Rule
    public TestName testName = new TestName();

    Logger logger = LoggerFactory.getLogger(StudentRepositoryTest.class);

    @Before
    public void setUp() throws Exception {
        List<Address> addresses= new ArrayList<>();
        addresses.add(new Address("street", 1));

        Iterable<Address> a = addressRepository.save(addresses);
        List<Address> target = new ArrayList<>();
        a.forEach(target::add);

        student1 = new Student();
        student1.setName("John Doe");
        student1.setAddress(target);
        studentRepository.save(student1);
    }

    @After
    public void after(){
        logger.info("Finished test " + testName.getMethodName());
        studentRepository.deleteAll();
    }

    @Test
    public void testSaveStudent(){
        List<Address> addresses= new ArrayList<>();
        addresses.add(new Address("my street", 1));

        Iterable<Address> a = addressRepository.save(addresses);
        List<Address> target = new ArrayList<>();
        a.forEach(target::add);

        Student student = new Student();
        student.setName("Lorem Ipsum");
        student.setAddress(target);

        assertNull(student.getId());
        studentRepository.save(student);
        assertNotNull(student.getId());
    }

    @Test
    public void testGetAllStudents(){
        Iterable<Student> students = studentRepository.findAll();
        long size = students.spliterator().getExactSizeIfKnown();
        assertEquals(size, 1);
    }

    @Test
    public void testRemoveStudent(){
        studentRepository.delete(student1);
        assertNull(studentRepository.findOne(student1.getId()));
    }

    @Test
    public void testFindById(){
        Student found = studentRepository.findOne(student1.getId());
        assertNotNull(found.getId());
        assertEquals(found.getId(), student1.getId());
    }

    @Test
    public void testUpdateStudent(){
        student1.setName("New name");
        Student updatedStudent = studentRepository.save(student1);
        assertEquals(updatedStudent.getName(), "New name");
    }
}
