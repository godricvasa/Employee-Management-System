package com.QuizRest.QuizApp.repository;

import com.QuizRest.QuizApp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
