package com.Testing.MockitoTesting.controller;

import com.Testing.MockitoTesting.Entity.Employee;
import com.Testing.MockitoTesting.dto.EmployeeDTO;
import com.Testing.MockitoTesting.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/home")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;
    EmployeeController(EmployeeService employeeService,ModelMapper modelMapper){
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{empId}")
    public EmployeeDTO getEmployeeById(@PathVariable("empId") Long id){
        Employee singleEmployee = employeeService.getEmployee(id);
        return modelMapper.map(singleEmployee,EmployeeDTO.class);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElement(NoSuchElementException ex){
        System.out.println("inside the function");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO dto = employeeService.createNewEmployee(employeeDTO);
        System.out.println(dto.getName());
        return dto;
    }
}
