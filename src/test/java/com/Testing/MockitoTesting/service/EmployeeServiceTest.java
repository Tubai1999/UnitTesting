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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

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
        Optional<Employee> employee = employeeService.getEmployee(mockedEmployee.getId());

        //Assert
        assertThat(employee.get().getId()).isEqualTo(mockedEmployee.getId());
        assertThat(employee.get().getName()).isEqualTo("Tridib");
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

}
