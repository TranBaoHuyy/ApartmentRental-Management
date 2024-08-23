package dao;

import entity.ApartmentType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.DBCon;

public class ApartmentTypeDAO {

    public boolean addApartmentType(ApartmentType apartmentType) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL addApartmentType(?, ?)}");
        ) {
            callableStatement.setString(1, apartmentType.getType());
            callableStatement.setDouble(2, apartmentType.getPrice());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ApartmentType> getAllApartmentTypes() {
        List<ApartmentType> apartmentTypes = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllApartmentTypes}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                ApartmentType apartmentType = new ApartmentType();

                apartmentType.setId(resultSet.getInt("id"));
                apartmentType.setType(resultSet.getString("type"));
                apartmentType.setPrice(resultSet.getDouble("price"));

                apartmentTypes.add(apartmentType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartmentTypes;
    }

    public boolean updateApartmentType(ApartmentType apartmentType) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL updateApartmentType(?, ?, ?)}");
        ) {
            callableStatement.setInt(1, apartmentType.getId());
            callableStatement.setString(2, apartmentType.getType());
            callableStatement.setDouble(3, apartmentType.getPrice());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteApartmentType(int id) throws SQLIntegrityConstraintViolationException {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL deleteApartmentType(?)}");
        ) {
            callableStatement.setInt(1, id);

            int rowsAffected = callableStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                throw new SQLIntegrityConstraintViolationException("No rows affected. Probably the ApartmentType with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException("Error while deleting Apartment. Details: " + e.getMessage());
        }
    }

    public ApartmentType getApartmentTypeById(int id) {
        ApartmentType apartmentType = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getApartmentTypeById(?)}");
        ) {
            callableStatement.setInt(1, id);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    apartmentType = new ApartmentType();
                    apartmentType.setId(resultSet.getInt("id"));
                    apartmentType.setType(resultSet.getString("type"));
                    apartmentType.setPrice(resultSet.getDouble("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartmentType;
    }

    public List<Integer> getAllApartmentTypeIds() {
        List<Integer> apartmentTypeIds = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllApartmentTypeIds}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                apartmentTypeIds.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartmentTypeIds;
    }

    public String getApartmentTypeNameById(int id) {
        String typeName = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getApartmentTypeNameById(?)}");
        ) {
            callableStatement.setInt(1, id);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                    typeName = resultSet.getString("type");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeName;
    }
}
