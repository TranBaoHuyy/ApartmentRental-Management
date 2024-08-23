package dao;

import entity.Household;
import utils.ShowMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.DBCon;

public class HouseholdDAO {

    public boolean addHousehold(Household household) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL addHousehold(?, ?, ?, ?, ?, ?, ?, ?)}");
        ) {
            callableStatement.setString(1, household.getIdNumber());
            callableStatement.setString(2, household.getHouseholdHeadName());
            callableStatement.setString(3, household.getPhoneNumber());
            callableStatement.setDate(4, new java.sql.Date(household.getDateOfBirth().getTime()));
            callableStatement.setString(5, household.getEmail());
            callableStatement.setString(6, household.getImage());
            callableStatement.setString(7, household.getGender());
            callableStatement.setInt(8, household.getMemberQuantity());

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

    public List<Household> getAllHouseholds() {
        List<Household> households = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllHouseholds}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Household household = new Household();

                household.setIdNumber(resultSet.getString("id_number"));
                household.setHouseholdHeadName(resultSet.getString("household_head_name"));
                household.setPhoneNumber(resultSet.getString("phone_number"));
                household.setDateOfBirth(resultSet.getDate("date_of_birth"));
                household.setEmail(resultSet.getString("email"));
                household.setImage(resultSet.getString("image"));
                household.setGender(resultSet.getString("gender"));
                household.setMemberQuantity(resultSet.getInt("member_quantity"));

                households.add(household);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return households;
    }

    public boolean updateHousehold(Household household) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL updateHousehold(?, ?, ?, ?, ?, ?, ?, ?)}");
        ) {
            callableStatement.setString(1, household.getIdNumber());
            callableStatement.setString(2, household.getHouseholdHeadName());
            callableStatement.setString(3, household.getPhoneNumber());
            callableStatement.setDate(4, new java.sql.Date(household.getDateOfBirth().getTime()));
            callableStatement.setString(5, household.getEmail());
            callableStatement.setString(6, household.getImage());
            callableStatement.setString(7, household.getGender());
            callableStatement.setInt(8, household.getMemberQuantity());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteHousehold(String idNumber) throws SQLIntegrityConstraintViolationException {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL deleteHousehold(?)}");
        ) {
            callableStatement.setString(1, idNumber);

            int rowsAffected = callableStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                throw new SQLIntegrityConstraintViolationException("No rows affected. Probably the Household with ID " + idNumber + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException("Error while deleting Household. Details: " + e.getMessage());
        }
    }

    public Household getHouseholdById(String idNumber) {
        Household household = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getHouseholdById(?)}");
        ) {
            callableStatement.setString(1, idNumber);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    household = new Household();
                    household.setIdNumber(resultSet.getString("id_number"));
                    household.setHouseholdHeadName(resultSet.getString("household_head_name"));
                    household.setPhoneNumber(resultSet.getString("phone_number"));
                    household.setDateOfBirth(resultSet.getDate("date_of_birth"));
                    household.setEmail(resultSet.getString("email"));
                    household.setImage(resultSet.getString("image"));
                    household.setGender(resultSet.getString("gender"));
                    household.setMemberQuantity(resultSet.getInt("member_quantity"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return household;
    }

    public List<String> getAllHouseholdIds() {
        List<String> householdIds = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllHouseholdIds}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                String idNumber = resultSet.getString("id_number");
                householdIds.add(idNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return householdIds;
    }

    public String getHouseholdHeadNameById(String idNumber) {
        String householdHeadName = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getHouseholdHeadNameById(?)}");
        ) {
            callableStatement.setString(1, idNumber);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    householdHeadName = resultSet.getString("household_head_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return householdHeadName;
    }
}
