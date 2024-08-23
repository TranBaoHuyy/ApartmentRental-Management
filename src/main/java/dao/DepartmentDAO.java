package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBCon;
import entity.Department;

public class DepartmentDAO {

    public boolean addDepartment(Department department) {
        try (
            Connection connection = DBCon.getConn();
        	CallableStatement callableStatement = connection.prepareCall("{CALL addDepartment(?)}");
        ) {
            callableStatement.setString(1, department.getName());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
        	CallableStatement callableStatement = connection.prepareCall("{CALL getAllDepartments}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Department department = new Department();

                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));

                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departments;
    }

    public boolean updateDepartment(Department department) {
        try (
            Connection connection = DBCon.getConn();
        	CallableStatement callableStatement = connection.prepareCall("{CALL updateDepartment(?, ?)}");
        ) {
            callableStatement.setInt(1, department.getId());
            callableStatement.setString(2, department.getName());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteDepartment(int departmentId) {
        try (
            Connection connection = DBCon.getConn();
        	CallableStatement callableStatement = connection.prepareCall("{CALL deleteDepartment(?)}");
        ) {
            callableStatement.setInt(1, departmentId);

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public String getDepartmentNameById(int departmentId) {
        String departmentName = null;

        try (
            Connection connection = DBCon.getConn();
        		CallableStatement callableStatement = connection.prepareCall("{CALL getDepartmentNameById (?)}");
        ) {
        	callableStatement.setInt(1, departmentId);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                	departmentName = resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departmentName;
    }
    
    public List<Integer> getAllDepartmentIds() {
        List<Integer> departmentIds = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllDepartmentIds}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                int departmentId = resultSet.getInt("id");
                departmentIds.add(departmentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departmentIds;
    }
    
    public Department getDepartmentById(int departmentId) {
    	Department department = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getDepartmentById(?)}");
        ) {
            callableStatement.setInt(1, departmentId);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                	department = new Department();
                	department.setId(resultSet.getInt("id"));
                	department.setName(resultSet.getString("name"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return department;
    }
}
