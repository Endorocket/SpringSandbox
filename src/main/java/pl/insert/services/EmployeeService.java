package pl.insert.services;

import pl.insert.data.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployeeList();

    Employee getEmployeeById(Long empId);

    void insertEmployee(Employee emp);

    void deleteEmployee(Long empId);

    Employee updateEmployee(Employee emp);
}
