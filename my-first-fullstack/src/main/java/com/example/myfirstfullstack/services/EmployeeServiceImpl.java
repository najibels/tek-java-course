package com.example.myfirstfullstack.services;

import com.example.myfirstfullstack.models.Employee;
import com.example.myfirstfullstack.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Primary
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    private Employee employee;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        Employee employee;
        try {
            employee = employeeRepository.getById(id);
        } catch (EntityNotFoundException e) {
            throw new EmployeeNotFoundException();
        }
        return  employee;
    }

    @Override
    public Employee getEmployeeByName(String name) {
        Employee employee = employeeRepository.findFirstByLastName(name);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return  employee;
    }

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }
}
