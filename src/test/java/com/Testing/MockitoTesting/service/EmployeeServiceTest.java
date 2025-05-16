package com.Testing.MockitoTesting.service;

import com.Testing.MockitoTesting.Entity.Employee;
import com.Testing.MockitoTesting.TestContainerConfiguration;
import com.Testing.MockitoTesting.dto.EmployeeDTO;
import com.Testing.MockitoTesting.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//@DataJpaTest
@ExtendWith(MockitoExtension.class)
@Import(TestContainerConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void testFindById_whenIdIsPresent_returnEmployeeDTO(){

        Employee mockedEmployee = Employee.builder()
                .name("Tridib")
                .yearOfExperience(12)
                .build();

        //Arrage
        when(employeeRepository.findById(mockedEmployee.getId())).thenReturn(Optional.of(mockedEmployee));

        //Act
        Employee employee = employeeService.getEmployee(mockedEmployee.getId());

        //Assert
        assertThat(employee.getId()).isEqualTo(mockedEmployee.getId());
        assertThat(employee.getName()).isEqualTo("Tridib");
    }

    @Test
    void testFindById_whenIdIsNotPresent_throwException(){
        //Arrange
        when(employeeRepository.findById(999L)).thenReturn(Optional.empty());

        //Act and Assert
        assertThatThrownBy(()-> employeeService.getEmployee(999L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Resource not found");
    }

    @Test
    void createNewEmployee_returnEmployeeDTO(){
        Employee mockedEmployee = Employee.builder()
                .name("Nitin")
                        .yearOfExperience(20)
                                .build();
        EmployeeDTO mockEmployeeDto = modelMapper.map(mockedEmployee,EmployeeDTO.class);
        //Arrange
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockedEmployee);
        //Act
        EmployeeDTO employeeDTO = employeeService.createNewEmployee(mockEmployeeDto);
        //Assert

        assertThat(employeeDTO.getName()).isEqualTo(mockedEmployee.getName());
    }

    @Test
    void failToCreateEmployee_throwException(){
        Employee mockedEmployee = Employee.builder()
                .name("Nitin")
                .yearOfExperience(200)
                .build();
        //Arrange
        when(employeeRepository.findByYearOfExperience(200))
                .thenReturn(List.of(mockedEmployee));

        EmployeeDTO mockEmployeeDto = modelMapper.map(mockedEmployee,EmployeeDTO.class);
        //Act //Assert
        assertThatThrownBy(()->employeeService.createNewEmployee(mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("same yearOfExperience already exit");

        verify(employeeRepository,never()).save(any());
    }

}
