package com.Testing.MockitoTesting.repository;

import com.Testing.MockitoTesting.Entity.Employee;
import com.Testing.MockitoTesting.TestContainerConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
@Import(TestContainerConfiguration.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp(){
        employee = Employee.builder()
//                .id(22L)
                .name("Tridib")
                .yearOfExperience(12)
                .build();
    }

    @Test
    void testFindById_whenIdIsPresent_thenReturnEmployee() {
           Employee savedEmployee = employeeRepository.save(employee);
        Optional<Employee> singleEmployee = employeeRepository.findById(savedEmployee.getId());
        assertThat(singleEmployee).isPresent();
        assertThat(singleEmployee.get().getName()).isEqualTo("Tridib");

//        assertThat(singleEmployee).isPresent().hasValueSatisfying((emp) -> assertThat(Objects.equals(emp.getName(), "Tidib")));

    }

    @Test
    void testFindById_whenIdIsNotPresent_thenReturnNull(){
        Optional<Employee> singleEmployee= employeeRepository.findById(99L);
        assertThat(singleEmployee).isNotPresent();
    }
}