package com.Testing.MockitoTesting.repository;

import com.Testing.MockitoTesting.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
//    public Employee findById();
    Optional<Employee> findById(Long id);
}
