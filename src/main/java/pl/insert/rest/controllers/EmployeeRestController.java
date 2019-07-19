package pl.insert.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.insert.data.Employee;
import pl.insert.rest.EmployeeNotFoundException;
import pl.insert.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.getEmployeeList();

        return employees;
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }

        return employee;
    }

    @PostMapping("/employees")
    public String addEmployee(@RequestBody Employee employee) {
        employeeService.insertEmployee(employee);

        return "Employee added";
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        return updatedEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }

        employeeService.deleteEmployee(employeeId);

        return "Deleted employee id - " + employeeId;
    }
}
