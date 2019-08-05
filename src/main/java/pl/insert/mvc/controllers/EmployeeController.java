package pl.insert.mvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.insert.data.Employee;
import pl.insert.services.EmployeeService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    public final static String IP_ADDRESS = "127.0.0.1/8";

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getEmployees(Model model) {
        log.info("inside getEmployees");
        List<Employee> employees = employeeService.getEmployeeList();
        model.addAttribute("employees", employees);

        return "employees/list-employees";
    }

    @GetMapping("/add")
    @PreAuthorize("hasIpAddress(T(pl.insert.mvc.controllers.EmployeeController).IP_ADDRESS)")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();
        employee.setJoinedOn(new Date());
        model.addAttribute("employee", employee);

        return "employees/add-form";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult result) {
        log.info("inside addEmployee");

        if (result.hasErrors()) {
            return "employees/add-form";
        }

        employeeService.insertEmployee(employee);

        return "redirect:/employees";
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("employeeId") long employeeId, Model model) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employee);

        return "employees/update-form";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult result) {
        log.info("inside updateEmployee");

        if (result.hasErrors()) {
            return "employees/update-form";
        }

        employeeService.updateEmployee(employee);

        return "redirect:/employees";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") long employeeId) {
        log.info("inside deleteEmployee");
        employeeService.deleteEmployee(employeeId);

        return "redirect:/employees";
    }
}
