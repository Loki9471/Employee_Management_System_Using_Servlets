package com.codegnan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.codegnan.dto.Employee;
import com.codegnan.factories.ConnectionFactory;

public class EmployeeDaoImp1 implements EmployeeDao {

    @Override
    public String add(Employee employee) {
        String status = "";
        try {
            Employee emp = search(employee.getEno());
            if (emp == null) {
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO emp2 VALUES(?,?,?,?)");
                preparedStatement.setInt(1, employee.getEno());
                preparedStatement.setString(2, employee.getEname());
                preparedStatement.setFloat(3, employee.getEsal());
                preparedStatement.setString(4, employee.getEaddr());
                int rowCount = preparedStatement.executeUpdate();
                if (rowCount == 1) {
                    status = "success";
                } else {
                    status = "failure";
                }
            } else {
                status = "existed";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Employee search(int eno) {
        Employee employee = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM emp2 WHERE eno=?");
            preparedStatement.setInt(1, eno);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee();
                employee.setEno(resultSet.getInt("eno"));
                employee.setEname(resultSet.getString("ename"));
                employee.setEsal(resultSet.getFloat("esal"));
                employee.setEaddr(resultSet.getString("eaddr"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public String update(Employee employee) {
        String status = "";
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE emp2 SET ename=?, esal=?, eaddr=? WHERE eno=?");
            preparedStatement.setString(1, employee.getEname());
            preparedStatement.setFloat(2, employee.getEsal());
            preparedStatement.setString(3, employee.getEaddr());
            preparedStatement.setInt(4, employee.getEno());
            int rowCount = preparedStatement.executeUpdate();
            if (rowCount == 1) {
                status = "success";
            } else {
                status = "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public String delete(int eno) {
        String status = "";
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM emp2 WHERE eno=?");
            preparedStatement.setInt(1, eno);
            int rowCount = preparedStatement.executeUpdate();
            if (rowCount == 1) {
                status = "success";
            } else {
                status = "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
