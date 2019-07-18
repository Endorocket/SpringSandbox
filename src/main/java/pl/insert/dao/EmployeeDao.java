package pl.insert.dao;

import pl.insert.data.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getEmployeeList();

    Employee getEmployeeById(Long empId);

    void insertEmployee(Employee emp);

    void deleteEmployee(Long empId);

    Employee updateEmployee(Employee emp);
}
