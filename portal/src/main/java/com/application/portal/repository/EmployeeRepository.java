package com.application.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.portal.domain.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    void deleteEmployeeById(Long id);

    Optional<Employee> findEmployeeById(Long id);
}
