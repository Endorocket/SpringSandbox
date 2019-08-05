package pl.insert.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.insert.dao.EmployeeDao;
import pl.insert.data.Employee;

import java.util.List;

@Service(value = "employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getEmployeeList() {
        List<Employee> employees = employeeDao.getEmployeeList();
        return employees;
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        return employeeDao.getEmployeeById(empId);
    }

    @Override
    public void insertEmployee(Employee emp) {
        emp.setEmpId(null);
        employeeDao.insertEmployee(emp);
    }

    @Override
    public void deleteEmployee(Long empId) {
        employeeDao.deleteEmployee(empId);
    }

    @Override
    public Employee updateEmployee(Employee emp) {
        Employee employee = employeeDao.updateEmployee(emp);
        return employee;
    }

    @Override
    public String toString() {
        return "EmployeeServiceImpl{" +
                "employeeDao=" + employeeDao +
                '}';
    }
}
