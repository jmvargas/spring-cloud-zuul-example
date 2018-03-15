package com.privalia.finalproject.respository;

import com.privalia.finalproject.config.RepositoryConfiguration;
import com.privalia.finalproject.domain.Address;
import com.privalia.finalproject.repository.AddressRepository;
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
public class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;
    private Address address1 = null;

    @Rule
    public TestName testName = new TestName();

    Logger logger = LoggerFactory.getLogger(AddressRepositoryTest.class);

    @Before
    public void setUp() throws Exception {
        address1 = new Address();
        address1.setStreet("John Doe");
        address1.setNumber(12);
        addressRepository.save(address1);
    }

    @After
    public void after(){
        logger.info("Finished test " + testName.getMethodName());
        addressRepository.deleteAll();
    }

    @Test
    public void testSaveAddress(){
        Address address = new Address();
        address.setStreet("Lorem Ipsum");
        address.setNumber(14);
        
        assertNull(address.getId());
        addressRepository.save(address);
        assertNotNull(address.getId());
    }

    @Test
    public void testGetAllAddress(){
        address1.setStreet("New name");
        Address updatedAddress = addressRepository.save(address1);
        assertEquals(updatedAddress.getStreet(), "New name");
    }
}
