package com.Testing.MockitoTesting.controller;

import com.Testing.MockitoTesting.Entity.Employee;
import com.Testing.MockitoTesting.TestContainerConfiguration;
import com.Testing.MockitoTesting.dto.EmployeeDTO;
import com.Testing.MockitoTesting.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "100000")
@Import(TestContainerConfiguration.class)
class EmployeeControllerTestIT {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp(){
         employee = Employee.builder()
                .name("Tridib")
                .yearOfExperience(12)
                .build();

        employeeDTO = EmployeeDTO.builder()
                .name("Tridib")
                .yearOfExperience(12)
                .build();
    }

    @Test
    void testGetEmployeeById_success(){
        Employee savedEmployee = employeeRepository.save(employee);
        System.out.println(savedEmployee.getName());
        System.out.println(savedEmployee.getId());
        webTestClient.get()
                .uri("/home/{id}",savedEmployee.getId())
                .exchange()
                .expectBody(EmployeeDTO.class)
//                .isEqualTo(employeeDTO);
                .value(dto ->{
                    assertThat(dto.getName()).isEqualTo(savedEmployee.getName());
                        }
                        );
    }

}