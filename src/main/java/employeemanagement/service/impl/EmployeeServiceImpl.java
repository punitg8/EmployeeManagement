package employeemanagement.service.impl;

import employeemanagement.exception.ResourceNotFoundException;
import employeemanagement.model.Employee;
import employeemanagement.repositry.EmployeeRepositry;
import employeemanagement.service.EmployeeService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepositry employeeRepositry;

  @Override
  public Employee saveEmployee(Employee employee) {
    return employeeRepositry.save(employee);
  }

  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepositry.findAll();
  }

  @Override
  public Employee getEmployeeById(long id) {
    return employeeRepositry.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", Long.toString(id)));
  }

  @Override
  public Employee updateEmployee(Employee employee, long id) {
    Employee existingEmployee = employeeRepositry.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", Long.toString(id)));

    existingEmployee.setFirstName(employee.getFirstName());
    existingEmployee.setLastName(employee.getLastName());
    existingEmployee.setEmailId(employee.getEmailId());

    return employeeRepositry.save(existingEmployee);
  }

  @Override
  public void deleteEmployee(long id) {
    employeeRepositry.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", Long.toString(id)));

    employeeRepositry.deleteById(id);
  }

}
