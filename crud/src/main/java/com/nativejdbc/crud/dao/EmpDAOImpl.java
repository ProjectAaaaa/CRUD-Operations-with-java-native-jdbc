package com.nativejdbc.crud.dao;

import com.nativejdbc.crud.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmpDAOImpl implements EmpDAO{

    @Autowired
    DataSource dataSource;

    public final Connection getFinalConnection(){
        try {
            Connection connection = dataSource.getConnection();
            if(connection != null){
                System.out.println("Connected to Database");
            }else{
                System.out.println("Not Connected!!");
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> getAll() {

        try {
            //db connection
            Connection connection = getFinalConnection();
            String sql = "select * from user_data";

            //execution
            PreparedStatement statement  = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            //to store multiple employee details
            ArrayList<Employee> employees = new ArrayList<>();

            //iterate
            while (rs.next()){

                Employee employee = new Employee();

                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail_id(rs.getString("email_id"));
                employee.setMobile_number(rs.getLong("mobile_number"));
                employee.setStatus(rs.getString("status"));

                employees.add(employee);

            }
            return employees;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Employee getOne(int id) {
        try {
            //db connection
            Connection connection = getFinalConnection();
            String sql = "select * from user_data where id=?";

            //Prepared Statement
            PreparedStatement statement = connection.prepareStatement(sql);

            //based on id
            statement.setInt(1,id);

            //Execution
            ResultSet rs = statement.executeQuery();

            //iterate
            if (rs.next()){
                Employee employee = new Employee();

                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail_id(rs.getString("email_id"));
                employee.setMobile_number(rs.getLong("mobile_number"));
                employee.setStatus(rs.getString("status"));

                return employee;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int addEmployee(Employee employee) {

        try {

            Connection connection = getFinalConnection();
            String sql = "insert into user_data (id , name , email_id , mobile_number , status) values (?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,employee.getId());
            statement.setString(2,employee.getName());
            statement.setString(3,employee.getEmail_id());
            statement.setLong(4,employee.getMobile_number());
            statement.setString(5,employee.getStatus());

            int count = statement.executeUpdate();
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateEmployee(Employee employee, int id) {

        try {

            Connection connection = getFinalConnection();
            String sql = "update user_data set id=? , name=? , email_id=? , mobile_number=? , status=? where id=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,employee.getId());
            statement.setString(2,employee.getName());
            statement.setString(3,employee.getEmail_id());
            statement.setLong(4,employee.getMobile_number());
            statement.setString(5,employee.getStatus());
            
            statement.setInt(6,id);

            int count = statement.executeUpdate();

            return count;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteEmployee(int id) {

        try {
            Connection connection = getFinalConnection();
            String sql = "delete from user_data where id=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,id);

            int count = statement.executeUpdate();
            return count;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
