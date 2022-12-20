package com.nativejdbc.crud.dao;

import com.nativejdbc.crud.model.Employee;

import java.util.List;

public interface EmpDAO {
    List<Employee> getAll();
    Employee getOne(int id);
    int addEmployee(Employee employee);
    int updateEmployee(Employee employee , int id);
    int deleteEmployee(int id);
}
