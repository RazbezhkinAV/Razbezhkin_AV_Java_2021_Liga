package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.dto.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.persistence.CustomerRepository;
import com.example.liquibasedemo.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/crud/customer")
@RequiredArgsConstructor //Добавляет конструктор с аннотацией @Autowired к существующим полям.
@Api(value = "Customer CRUD operations", description = "Customer CRUD operations")
public class CustomerController {

    private final CustomerService customerService;



    @ApiOperation(value = "Enumerates all Customer entities")
    @GetMapping
    public Page<CustomerDTO> enumerate(@RequestParam Optional<Integer> page,
                                       @RequestParam Optional<Integer> size,
                                       @RequestParam Optional<String> sortBy) {
        return customerService.enumerate(PageRequest.of(
                page.orElse(0),
                size.orElse(5),
                Sort.Direction.ASC,sortBy.orElse("id")));

    }

    @ApiOperation(value = "Store given Customer entity")
    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @ApiOperation(value = "Retrieves Customer entity by it ID")
    @GetMapping("/{id}")
    public CustomerDTO get(@PathVariable("id") String id) {
        return customerService.get(id);
    }

    @ApiOperation(value = "Delete Customer entity")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") String id){
        customerService.delete(id);
    }

    @ApiOperation(value = "Create Customer")
    @PutMapping("/create")
    public void create(@RequestBody Customer customer){
        customerService.create(customer);
    }

    //TODO: добавить и проаннотировать операции удаления сущности Customer, и создания новой пустой сущности Customer
}
