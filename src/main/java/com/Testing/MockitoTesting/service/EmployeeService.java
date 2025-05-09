package com.Testing.MockitoTesting.service;

import com.Testing.MockitoTesting.Entity.Employee;
import com.Testing.MockitoTesting.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> getEmployee(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
//        System.out.println("inisde service layer");
//       employee.ifPresent((e)-> System.out.println(e.getName()));
        return employee;
    }
}
