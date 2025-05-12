package com.Testing.MockitoTesting.service;

import com.Testing.MockitoTesting.Entity.Employee;
import com.Testing.MockitoTesting.dto.EmployeeDTO;
import com.Testing.MockitoTesting.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<Employee> getEmployee(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
//        System.out.println("inisde service layer");
//       employee.ifPresent((e)-> System.out.println(e.getName()));
        return employee;
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO){
//        Employee newEmployee = modelMapper.map(employeeDTO,Employee.class);
        Employee newEmployee = new Employee();
        newEmployee.setName(employeeDTO.getName());
        newEmployee.setYearOfExperience(employeeDTO.getYearOfExperience());
        Employee savedEmployee = employeeRepository.save(newEmployee);
        System.out.println(savedEmployee.getName());
        return modelMapper.map(savedEmployee,EmployeeDTO.class);
    }
}
