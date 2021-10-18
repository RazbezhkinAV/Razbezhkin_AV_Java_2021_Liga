package com.example.liquibasedemo.util;

import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.dto.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class MappingDTO {

    public CustomerDTO mapToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        return customerDTO;
    }

}
