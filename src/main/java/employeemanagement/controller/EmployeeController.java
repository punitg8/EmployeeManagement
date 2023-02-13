package employeemanagement.controller;

import employeemanagement.model.Employee;
import employeemanagement.service.EmployeeService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

  private EmployeeService employeeService;

  @PostMapping()
  public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
    return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
  }

  @GetMapping("{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id){
    return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
  }

  @GetMapping()
  public ResponseEntity<List<Employee>> getAllEmployees(){
    return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable("id") long id){
    return new ResponseEntity<>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
    employeeService.deleteEmployee(id);
    return new ResponseEntity<>("Employee was deleted",HttpStatus.OK);
  }
}
