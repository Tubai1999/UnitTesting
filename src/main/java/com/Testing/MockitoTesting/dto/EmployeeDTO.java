package com.Testing.MockitoTesting.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private int yearOfExperience;
    private String name;


}
