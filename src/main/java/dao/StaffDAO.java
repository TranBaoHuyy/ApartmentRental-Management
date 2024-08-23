package dao;

import entity.Staff;
import utils.ShowMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.DBCon;

public class StaffDAO {

    public boolean addStaff(Staff staff) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL addStaff(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        ) {
            callableStatement.setString(1, staff.getId());
            callableStatement.setString(2, staff.getName());
            callableStatement.setBoolean(3, staff.isGender());
            callableStatement.setString(4, staff.getAddress());
            callableStatement.setString(5, staff.getPhoneNumber());
            callableStatement.setDate(6, new java.sql.Date(staff.getDob().getTime()));
            callableStatement.setString(7, staff.getEmail());
            callableStatement.setString(8, staff.getImage());
            callableStatement.setString(9, staff.getIdNumber());
            callableStatement.setInt(10, staff.getDepartmentId());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
        	if (e.getErrorCode() == 2627) {
                ShowMessage.showWarningMessage("Warning", "ID Number already exists. Please choose a different ID Number.");
            } else {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Staff> getAllStaffs() {
        List<Staff> staffs = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllStaffs}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Staff staff = new Staff();

                staff.setId(resultSet.getString("id"));
                staff.setName(resultSet.getString("name"));
                staff.setGender(resultSet.getBoolean("gender"));
                staff.setAddress(resultSet.getString("address"));
                staff.setPhoneNumber(resultSet.getString("phone_number"));
                staff.setDob(resultSet.getDate("dob"));
                staff.setEmail(resultSet.getString("email"));
                staff.setImage(resultSet.getString("image"));
                staff.setIdNumber(resultSet.getString("id_number"));
                staff.setDepartmentId(resultSet.getInt("department_id"));

                staffs.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffs;
    }

    public boolean updateStaff(Staff staff) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL updateStaff(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        ) {
            callableStatement.setString(1, staff.getId());
            callableStatement.setString(2, staff.getName());
            callableStatement.setBoolean(3, staff.isGender());
            callableStatement.setString(4, staff.getAddress());
            callableStatement.setString(5, staff.getPhoneNumber());
            callableStatement.setDate(6, new java.sql.Date(staff.getDob().getTime()));
            callableStatement.setString(7, staff.getEmail());
            callableStatement.setString(8, staff.getImage());
            callableStatement.setString(9, staff.getIdNumber());
            callableStatement.setInt(10, staff.getDepartmentId());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStaff(String staffId) throws SQLIntegrityConstraintViolationException {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL deleteStaff(?)}");
        ) {
            callableStatement.setString(1, staffId);

            int rowsAffected = callableStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                throw new SQLIntegrityConstraintViolationException("No rows affected. Probably the staff with ID " + staffId + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException("Error while deleting Staff. Details: " + e.getMessage());
        }
    }

    public String getStaffNameById(String staffId) {
        String staffName = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getStaffNameById(?)}");
        ) {
            callableStatement.setString(1, staffId);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    staffName = resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffName;
    }

    public List<String> getAllStaffIds() {
        List<String> staffIds = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllStaffIds}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                String staffId = resultSet.getString("id");
                staffIds.add(staffId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffIds;
    }

    public Staff getStaffById(String staffId) {
        Staff staff = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getStaffById(?)}");
        ) {
            callableStatement.setString(1, staffId);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    staff = new Staff();
                    staff.setId(resultSet.getString("id"));
                    staff.setName(resultSet.getString("name"));
                    staff.setGender(resultSet.getBoolean("gender"));
                    staff.setAddress(resultSet.getString("address"));
                    staff.setPhoneNumber(resultSet.getString("phone_number"));
                    staff.setDob(resultSet.getDate("dob"));
                    staff.setEmail(resultSet.getString("email"));
                    staff.setImage(resultSet.getString("image"));
                    staff.setIdNumber(resultSet.getString("id_number"));
                    staff.setDepartmentId(resultSet.getInt("department_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staff;
    }
    
    public List<Staff> getStaffsWithoutUser() {
        List<Staff> staffsWithoutUser = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL GetStaffsWithoutUser}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Staff staff = new Staff();

                staff.setId(resultSet.getString("id"));
                staff.setName(resultSet.getString("name"));

                staffsWithoutUser.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffsWithoutUser;
    }
}
