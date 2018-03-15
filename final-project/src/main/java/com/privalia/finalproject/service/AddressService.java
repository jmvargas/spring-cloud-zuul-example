package com.privalia.finalproject.service;

import com.privalia.finalproject.domain.Address;
import com.privalia.finalproject.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Iterable<Address> listAllAddresses() {
        return addressRepository.findAll();
    }

    public Address get(Integer id) {
        return addressRepository.findOne(id);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public void delete(Integer id) {
        addressRepository.delete(id);
    }

    public Address update(Integer id, Address address){
        Address old = this.get(id);
        old.setNumber(address.getNumber());
        old.setStreet(address.getStreet());
        return this.save(old);
    }

    public Iterable<Address> save(Iterable<Address> addresses){
        return addressRepository.save(addresses);
    }

    public void delete(Iterable<Address> addresses){
        addressRepository.delete(addresses);
    }
}