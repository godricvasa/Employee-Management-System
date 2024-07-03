package com.QuizRest.QuizApp.controller;


import com.QuizRest.QuizApp.dto.EmployeeDto;
import com.QuizRest.QuizApp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;


    //Build add empl rest api
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
       EmployeeDto saved =   employeeService.createEmployee(employeeDto);
       return new ResponseEntity<>(saved, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto fetched = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(fetched);
    }

    @GetMapping("/all")
    public  ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> allEmployees = employeeService.getAllEmployee();
        return ResponseEntity.ok(allEmployees);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,@RequestBody EmployeeDto employeeDto){
        EmployeeDto updated = employeeService.updateEmployee(employeeId,employeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id")  Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("The employee with the id "+employeeId+" id deleted successfully");
    }




}
