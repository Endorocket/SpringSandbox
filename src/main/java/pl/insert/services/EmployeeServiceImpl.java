package pl.insert.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.insert.dao.EmployeeDao;
import pl.insert.data.Employee;


import java.util.List;

@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    @Transactional
    public List<Employee> getEmployeeList() {
        List<Employee> employees = employeeDao.getEmployeeList();
        return employees;
    }

    @Override
    @Transactional
    public Employee getEmployeeById(Long empId) {
        return employeeDao.getEmployeeById(empId);
    }

    @Override
    @Transactional
    public void insertEmployee(Employee emp) {
        emp.setEmpId(null);
        employeeDao.insertEmployee(emp);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long empId) {
        employeeDao.deleteEmployee(empId);
    }

    @Override
    @Transactional
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
