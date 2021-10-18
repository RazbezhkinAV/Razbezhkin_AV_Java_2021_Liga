package com.example.liquibasedemo.service;

import com.example.liquibasedemo.dto.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.persistence.CustomerRepository;
import com.example.liquibasedemo.util.MappingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final MappingDTO mappingDTO;

    public Page<CustomerDTO> enumerate(PageRequest pageable) {
        Page<Customer> personPage = customerRepository.findAll(pageable);
        int totalElement = (int) personPage.getTotalElements();
        return new PageImpl<CustomerDTO>(personPage.stream()
                .map(mappingDTO::mapToCustomerDTO)
                .collect(Collectors.toList()),pageable,totalElement);

    }

    public Customer save( Customer customer) {
        return customerRepository.save(customer);
    }

    public CustomerDTO get(String id) {

        return mappingDTO.mapToCustomerDTO(customerRepository
                .findById(UUID.fromString(id))
                .get());
    }

    public void delete(String id){
        Customer customer = customerRepository
                .findById(UUID.fromString(id))
                .get();

        customerRepository.delete(customer);
    }

    public void create(Customer customer){
            customerRepository.save(customer);
    }

}
