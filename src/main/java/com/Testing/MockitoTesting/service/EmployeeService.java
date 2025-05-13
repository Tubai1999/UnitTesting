package com.Testing.MockitoTesting.service;

import com.Testing.MockitoTesting.Entity.Employee;
import com.Testing.MockitoTesting.dto.EmployeeDTO;
import com.Testing.MockitoTesting.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Employee getEmployee(Long id){
            return employeeRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Resource not found"));

//        Optional<Employee> employee = employeeRepository.findById(id);
//        System.out.println("inisde service layer");
//       employee.ifPresent((e)-> System.out.println(e.getName()));
//        return employee;
    }



    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO){
        List<Employee> employeeList = employeeRepository.findByYearOfExperience(employeeDTO.getYearOfExperience());
        if(!employeeList.isEmpty()){
           throw new RuntimeException("same yearOfExperience already exit");
        }
//        Employee newEmployee = modelMapper.map(employeeDTO,Employee.class);
        Employee newEmployee = new Employee();
        newEmployee.setName(employeeDTO.getName());
        newEmployee.setYearOfExperience(employeeDTO.getYearOfExperience());
        Employee savedEmployee = employeeRepository.save(newEmployee);
        System.out.println(savedEmployee.getName());
        return modelMapper.map(savedEmployee,EmployeeDTO.class);
    }
}
