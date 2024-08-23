package dao;

import entity.Apartment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.DBCon;

public class ApartmentDAO {

    public boolean addApartment(Apartment apartment) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL addApartment(?, ?, ?, ?, ?, ?)}");
        ) {
            callableStatement.setString(1, apartment.getId());
            callableStatement.setString(2, apartment.getNotes());
            callableStatement.setString(3, apartment.getAreaName());
            callableStatement.setString(4, apartment.getHouseholdId());
            callableStatement.setInt(5, apartment.getTypeId());
            callableStatement.setString(6, apartment.getStatus());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Apartment> getAllApartments() {
        List<Apartment> apartments = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllApartments}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Apartment apartment = new Apartment();

                apartment.setId(resultSet.getString("id"));
                apartment.setNotes(resultSet.getString("notes"));
                apartment.setAreaName(resultSet.getString("area_name"));
                apartment.setHouseholdId(resultSet.getString("household_id"));
                apartment.setTypeId(resultSet.getInt("type_id"));
                apartment.setStatus(resultSet.getString("status"));

                apartments.add(apartment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartments;
    }

    public boolean updateApartment(Apartment apartment) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL updateApartment(?, ?, ?, ?, ?, ?)}");
        ) {
            callableStatement.setString(1, apartment.getId());
            callableStatement.setString(2, apartment.getNotes());
            callableStatement.setString(3, apartment.getAreaName());
            callableStatement.setString(4, apartment.getHouseholdId());
            callableStatement.setInt(5, apartment.getTypeId());
            callableStatement.setString(6, apartment.getStatus());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteApartment(String id)throws SQLIntegrityConstraintViolationException {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL deleteApartment(?)}");
        ) {
            callableStatement.setString(1, id);

            int rowsAffected = callableStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                throw new SQLIntegrityConstraintViolationException("No rows affected. Probably the apartment with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException("Error while deleting Apartment. Details: " + e.getMessage());
        }
    }

    public Apartment getApartmentById(String id) {
        Apartment apartment = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getApartmentById(?)}");
        ) {
            callableStatement.setString(1, id);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    apartment = new Apartment();
                    apartment.setId(resultSet.getString("id"));
                    apartment.setNotes(resultSet.getString("notes"));
                    apartment.setAreaName(resultSet.getString("area_name"));
                    apartment.setHouseholdId(resultSet.getString("household_id"));
                    apartment.setTypeId(resultSet.getInt("type_id"));
                    apartment.setStatus(resultSet.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartment;
    }

    public List<String> getAllApartmentIds() {
        List<String> apartmentIds = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllApartmentIds}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                apartmentIds.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartmentIds;
    }

    public String getApartmentStatusById(String id) {
        String status = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getApartmentStatusById(?)}");
        ) {
            callableStatement.setString(1, id);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    status = resultSet.getString("status");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }
    
    public String getAreaNameById(String apartmentId) {
        String areaName = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAreaNameById(?)}");
        ) {
            callableStatement.setString(1, apartmentId);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    areaName = resultSet.getString("area_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return areaName;
    }
    
    public List<Apartment> getVacantApartments() {
        List<Apartment> vacantApartments = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getVacantApartments}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Apartment apartment = new Apartment();

                apartment.setId(resultSet.getString("id"));
                apartment.setNotes(resultSet.getString("notes"));
                apartment.setAreaName(resultSet.getString("area_name"));
                apartment.setHouseholdId(resultSet.getString("household_id"));
                apartment.setTypeId(resultSet.getInt("type_id"));
                apartment.setStatus(resultSet.getString("status"));

                vacantApartments.add(apartment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vacantApartments;
    }
    
    public List<Apartment> getOccupiedApartments() {
        List<Apartment> vacantApartments = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL GetOccupiedApartments}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Apartment apartment = new Apartment();

                apartment.setId(resultSet.getString("id"));
                apartment.setNotes(resultSet.getString("notes"));
                apartment.setAreaName(resultSet.getString("area_name"));
                apartment.setHouseholdId(resultSet.getString("household_id"));
                apartment.setTypeId(resultSet.getInt("type_id"));
                apartment.setStatus(resultSet.getString("status"));

                vacantApartments.add(apartment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vacantApartments;
    }
    
    public double getOccupancyRatio() {
        double occupancyRatio = 0.0;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL GetOccupancyRatio}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            if (resultSet.next()) {
                occupancyRatio = resultSet.getDouble("OccupancyRatio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return occupancyRatio;
    }
    
    public boolean updateApartmentStatusToOccupied(String apartmentId, String householdId) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL UpdateApartmentStatus(?, ?)}");
        ) {
            callableStatement.setString(1, apartmentId);
            callableStatement.setString(2, householdId);
            int rowsAffected = callableStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateApartmentStatusToVacant(String apartmentId) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL UpdateApartmentStatusToVacant(?)}");
        ) {
            callableStatement.setString(1, apartmentId);
            int rowsAffected = callableStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
