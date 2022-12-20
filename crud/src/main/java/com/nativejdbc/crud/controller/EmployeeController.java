package com.nativejdbc.crud.controller;

import com.nativejdbc.crud.dao.EmpDAOImpl;
import com.nativejdbc.crud.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmpDAOImpl empDAO;


    @GetMapping("users")
    public List<Employee> getEmployees(){
        return empDAO.getAll();
    }

    @GetMapping("users/{id}")
    public Employee getEmployee(@PathVariable int id){
        return empDAO.getOne(id);
    }

    @PostMapping("users")
    public String addEmployee(@RequestBody Employee employee){
        return empDAO.addEmployee(employee)+" row added";
    }

    @PutMapping("users/{id}")
    public String modifyEmployee(@PathVariable int id , @RequestBody Employee employee){
        return empDAO.updateEmployee(employee , id)+" row modified";
    }

    @DeleteMapping("users/{id}")
    public String deleteEmployee(@PathVariable int id){
        return empDAO.deleteEmployee(id)+" row deleted";
    }

}
