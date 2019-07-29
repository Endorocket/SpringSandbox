package pl.insert.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import pl.insert.data.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "employeeDao")
public class EmployeeDaoImpl implements EmployeeDao, InitializingBean, DisposableBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> getEmployeeList() {
        return (List<Employee>) entityManager.createQuery("SELECT e FROM Employee e").getResultList();
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        Employee employee = entityManager.find(Employee.class, empId);
        return employee;
    }

    @Override
    public void insertEmployee(Employee emp) {
        entityManager.persist(emp);
    }

    @Override
    public void deleteEmployee(Long empId) {

        Employee employee = entityManager.find(Employee.class, empId);

        if (employee != null) {
            entityManager.remove(employee);
        }
    }

    @Override
    public Employee updateEmployee(Employee emp) {
        Employee merged = entityManager.merge(emp);
        return merged;
    }

    private void init() {
        logger.info("inside init");
    }

    private void shutdown() {
        logger.info("inside shutdown");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    @Override
    public void destroy() throws Exception {
        shutdown();
    }

    @Override
    public String toString() {
        return "EmployeeDaoImpl{" +
                "entityManager=" + entityManager +
                '}';
    }
}
