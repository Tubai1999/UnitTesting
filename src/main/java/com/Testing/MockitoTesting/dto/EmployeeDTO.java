package com.Testing.MockitoTesting.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class EmployeeDTO {
    private Long id;
    private int yearOfExperience;
    private String name;
}
