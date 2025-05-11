package com.Testing.MockitoTesting.controller;

import com.Testing.MockitoTesting.Entity.Employee;
import com.Testing.MockitoTesting.dto.EmployeeDTO;
import com.Testing.MockitoTesting.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

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
        Employee singleEmployee = employeeService.getEmployee(id).orElse(null);
        return modelMapper.map(singleEmployee,EmployeeDTO.class);
    }

    @PostMapping()
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO){

        return employeeDTO;
    }
}
