package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.model.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.model.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity save(CustomerEntity customerEntity) {
        return this.customerRepository.save(customerEntity);
    }

    public CustomerEntity get(Long id) {
        return this.customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public CustomerEntity getByPetId(Long petId) {
        return this.customerRepository.findByPetsId(petId).orElseThrow(EntityNotFoundException::new);
    }

    public List<CustomerEntity> getAll() {
        return StreamSupport.stream(this.customerRepository.findAll().spliterator(), true)
            .collect(Collectors.toList());
    }
}
