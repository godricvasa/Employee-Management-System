package com.QuizRest.QuizApp.service.impl;

import com.QuizRest.QuizApp.dto.EmployeeDto;
import com.QuizRest.QuizApp.entity.Employee;
import com.QuizRest.QuizApp.exception.ResourceNotFoundException;
import com.QuizRest.QuizApp.mapper.EmployeeMapper;
import com.QuizRest.QuizApp.repository.EmployeeRepository;
import com.QuizRest.QuizApp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
       Employee savedEmp =  employeeRepository.save(employee);


        return EmployeeMapper.mapToEmployeeDto(savedEmp);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
   Employee employee = employeeRepository.findById(employeeId)
           .orElseThrow(()->new ResourceNotFoundException("Employee not found with id: "+employeeId));
   return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {

        List<Employee> allEmployees = employeeRepository.findAll();
       return allEmployees
               .stream()
               .map(EmployeeMapper::mapToEmployeeDto)
               .collect(Collectors.toList());



    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
       Employee employee =  employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("ID not found with id: "+employeeId));
       employee.setFirstName(updatedEmployee.getFirstName());
       employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

      Employee ChangedEmployee = employeeRepository.save(employee);

      return EmployeeMapper.mapToEmployeeDto(ChangedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee =  employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("ID not found with id: "+employeeId));


        employeeRepository.deleteById(employeeId);;
    }
}
