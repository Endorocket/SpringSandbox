package pl.insert.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.insert.config.JpaConfig;
import pl.insert.data.Employee;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {JpaConfig.class})
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void getEmployeeList() {
        List<Employee> employees = employeeService.getEmployeeList();

        employees.forEach(System.out::println);
    }

    @Test
    public void getEmployeeById() {
        Employee employee = employeeService.getEmployeeById(1L);

        System.out.println(employee);
    }

    @Test
    public void getEmployeeById_shouldThrowException() {
        Employee employee = employeeService.getEmployeeById(0L);

        System.out.println(employee);
    }

    @Test
    public void insertEmployee() {
        Employee employee = new Employee();
        employee.setDepartment("Government");
        employee.setJoinedOn(new Date());
        employee.setName("Robert L");
        employee.setSalary(10_000L);

        employeeService.insertEmployee(employee);
    }

    @Test
    public void deleteEmployee() {
        employeeService.deleteEmployee(11L);
    }

    @Test
    public void updateEmployee() {
        Employee employee = employeeService.getEmployeeById(2L);
        employee.setName("Anita A");

        employeeService.updateEmployee(employee);
    }
}