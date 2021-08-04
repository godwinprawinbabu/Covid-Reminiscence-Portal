package com.application.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.portal.domain.Employee;
import com.application.portal.exception.domain.UserNotFoundException;
import com.application.portal.repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee addEmployee(Employee employee){
        employee.setDateOfJoining(getCurrentTimeStamp());
        return employeeRepository.save(employee);
    }
    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }
    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public Employee findEmployeeByID(Long id) throws UserNotFoundException{
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(()-> new UserNotFoundException("User by ID:"+id+"was not found"));
    }
    public void deleteEmployee(Long id){
        employeeRepository.deleteEmployeeById(id);
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat formDate = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = formDate.format(new Date()); // option 2
        return strDate;
    }
}
