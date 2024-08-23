package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import database.DBCon;
import entity.Service;

public class ServiceDAO {

    public boolean addService(Service service) {
        try (
            var connection = DBCon.getConn();
        	var callableStatement = connection.prepareCall("{CALL addService(?, ?)}");
        ) {
            callableStatement.setString(1, service.getName());
            callableStatement.setFloat(2, service.getPrice());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();

        try (
            var connection = DBCon.getConn();
        	var callableStatement = connection.prepareCall("{CALL getAllServices}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Service service = new Service();

                service.setId(resultSet.getInt("id"));
                service.setName(resultSet.getString("name"));
                service.setPrice(resultSet.getFloat("price"));

                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }

    public boolean updateService(Service service) {
        try (
            var connection = DBCon.getConn();
        	var callableStatement = connection.prepareCall("{CALL updateService(?, ?, ?)}");
        ) {
            callableStatement.setInt(1, service.getId());
            callableStatement.setString(2, service.getName());
            callableStatement.setFloat(3, service.getPrice());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteService(int serviceId) throws SQLIntegrityConstraintViolationException {
        try (
            var connection = DBCon.getConn();
        	var callableStatement = connection.prepareCall("{CALL deleteService(?)}");
        ) {
            callableStatement.setInt(1, serviceId);

            int rowsAffected = callableStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                throw new SQLIntegrityConstraintViolationException("No rows affected. Probably the service with ID " + serviceId + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException("Error while deleting Apartment. Details: " + e.getMessage());
        }
    }
    
    public String getServiceNameById(int serviceId) {
        String serviceName = null;

        try (
            Connection connection = DBCon.getConn();
        		CallableStatement callableStatement = connection.prepareCall("{CALL getServiceNameById (?)}");
        ) {
        	callableStatement.setInt(1, serviceId);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                	serviceName = resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return serviceName;
    }
    
    public List<Integer> getAllServiceIds() {
        List<Integer> serviceIds = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllServiceIds}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                int serviceId = resultSet.getInt("id");
                serviceIds.add(serviceId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return serviceIds;
    }
    
    public Service getServiceById(int serviceId) {
    	Service service = null;

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getServiceById(?)}");
        ) {
            callableStatement.setInt(1, serviceId);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                	service = new Service();
                	service.setId(resultSet.getInt("id"));
                	service.setName(resultSet.getString("name"));
                	service.setPrice(resultSet.getFloat("price"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return service;
    }
}
