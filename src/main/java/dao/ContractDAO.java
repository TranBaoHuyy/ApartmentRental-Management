package dao;

import entity.Contract;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.DBCon;

public class ContractDAO {

    public boolean addContract(Contract contract) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL addContract(?, ?, ?, ?, ?, ?, ?)}");
        ) {
            callableStatement.setString(1, contract.getContractNumber());
            callableStatement.setDate(2, new java.sql.Date(contract.getStartDate().getTime()));
            callableStatement.setDate(3, new java.sql.Date(contract.getEndDate().getTime()));
            callableStatement.setString(4, contract.getApartmentId());
            callableStatement.setString(5, contract.getStaffId());
            callableStatement.setString(6, contract.getHouseholdIdNumber());
            callableStatement.setBoolean(7, contract.isStatus());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Contract> getAllContracts() {
        List<Contract> contracts = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllContracts}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Contract contract = new Contract();

                contract.setId(resultSet.getInt("id"));
                contract.setContractNumber(resultSet.getString("contract_number"));
                contract.setStartDate(resultSet.getDate("start_date"));
                contract.setEndDate(resultSet.getDate("end_date"));
                contract.setApartmentId(resultSet.getString("apartment_id"));
                contract.setStaffId(resultSet.getString("staff_id"));
                contract.setHouseholdIdNumber(resultSet.getString("household_id_number"));
                contract.setStatus(resultSet.getBoolean("status"));

                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contracts;
    }
    
    public List<Contract> getValidatedContracts() {
        List<Contract> contracts = new ArrayList<>();

        try (Connection connection = DBCon.getConn();
             PreparedStatement preparedStatement = connection.prepareStatement("{CALL getValidatedContract}");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Contract contract = new Contract();
                contract.setId(resultSet.getInt("id"));
                contract.setContractNumber(resultSet.getString("contract_number"));
                contract.setStartDate(resultSet.getDate("start_date"));
                contract.setEndDate(resultSet.getDate("end_date"));
                contract.setApartmentId(resultSet.getString("apartment_id"));
                contract.setStaffId(resultSet.getString("staff_id"));
                contract.setHouseholdIdNumber(resultSet.getString("household_id_number"));
                contract.setStatus(resultSet.getBoolean("status"));

                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contracts;
    }

    public boolean updateContract(Contract contract) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL updateContract(?, ?, ?, ?, ?, ?, ?, ?)}");
        ) {
            callableStatement.setInt(1, contract.getId());
            callableStatement.setString(2, contract.getContractNumber());
            callableStatement.setDate(3, new java.sql.Date(contract.getStartDate().getTime()));
            callableStatement.setDate(4, new java.sql.Date(contract.getEndDate().getTime()));
            callableStatement.setString(5, contract.getApartmentId());
            callableStatement.setString(6, contract.getStaffId());
            callableStatement.setString(7, contract.getHouseholdIdNumber());
            callableStatement.setBoolean(8, contract.isStatus());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteContract(int id) throws SQLIntegrityConstraintViolationException {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL deleteContract(?)}");
        ) {
            callableStatement.setInt(1, id);

            int rowsAffected = callableStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                throw new SQLIntegrityConstraintViolationException("No rows affected. Probably the Contract with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException("Error while deleting Apartment. Details: " + e.getMessage());
        }
    }

    public Contract getContractById(int id) {
        Contract contract = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getContractById(?)}");
        ) {
            callableStatement.setInt(1, id);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    contract = new Contract();
                    contract.setId(resultSet.getInt("id"));
                    contract.setContractNumber(resultSet.getString("contract_number"));
                    contract.setStartDate(resultSet.getDate("start_date"));
                    contract.setEndDate(resultSet.getDate("end_date"));
                    contract.setApartmentId(resultSet.getString("apartment_id"));
                    contract.setStaffId(resultSet.getString("staff_id"));
                    contract.setHouseholdIdNumber(resultSet.getString("household_id_number"));
                    contract.setStatus(resultSet.getBoolean("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contract;
    }

    public List<Integer> getAllContractIds() {
        List<Integer> contractIds = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllContractIds}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                contractIds.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contractIds;
    }

    public boolean getContractStatusById(int id) {
        boolean status = false;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getContractStatusById(?)}");
        ) {
            callableStatement.setInt(1, id);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    status = resultSet.getBoolean("status");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }
    
    public boolean updateContractStatusToFalse(int contractId) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL UpdateContractStatusToFalse(?)}");
        ) {
            callableStatement.setInt(1, contractId);
            int rowsAffected = callableStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
