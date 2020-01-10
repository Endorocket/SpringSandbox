package pl.insert.webflow.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.insert.data.Employee;
import pl.insert.services.EmployeeService;

import java.util.Date;

@Component
public class AddEmployeeHandler {

  private final EmployeeService employeeService;

  @Autowired
  public AddEmployeeHandler(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  public Employee init() {
    return new Employee();
  }

  public String enterJoinedOn(Employee model) {
    String transitionValue = "confirm";

//        Date date = new Date();
//        model.setJoinedOn(date);

    return transitionValue;
  }

  public String save(Employee model) {
    String transitionValue = "success";

    employeeService.insertEmployee(model);

    return transitionValue;
  }
}
