package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import database.DBCon;
import entity.ServiceInvoice;

public class ServiceInvoiceDAO {

	public boolean addServiceInvoice(ServiceInvoice serviceInvoice) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL addServiceInvoice(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        ) {
            callableStatement.setString(1, serviceInvoice.getName());
            callableStatement.setString(2, serviceInvoice.getStaffId());
            callableStatement.setDate(3, new java.sql.Date(serviceInvoice.getPrintingDate().getTime()));
            callableStatement.setDate(4, null);
            callableStatement.setString(5, serviceInvoice.getApartmentId());
            callableStatement.setString(6, serviceInvoice.getNotes());
            callableStatement.setInt(7, serviceInvoice.getServiceId());
            callableStatement.setInt(8, serviceInvoice.getQuantity());
            callableStatement.setDouble(9, serviceInvoice.getPrice());
            callableStatement.setBoolean(10, serviceInvoice.isStatus());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ServiceInvoice> getAllServiceInvoices() {
        List<ServiceInvoice> serviceInvoices = new ArrayList<>();

        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getAllServiceInvoices}");
            ResultSet resultSet = callableStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                ServiceInvoice serviceInvoice = new ServiceInvoice();

                serviceInvoice.setId(resultSet.getInt("id"));
                serviceInvoice.setName(resultSet.getString("name"));
                serviceInvoice.setStaffId(resultSet.getString("staff_id"));
                serviceInvoice.setPrintingDate(resultSet.getDate("printing_date"));
                serviceInvoice.setPaymentDate(resultSet.getDate("payment_date"));
                serviceInvoice.setApartmentId(resultSet.getString("apartment_id"));
                serviceInvoice.setNotes(resultSet.getString("notes"));
                serviceInvoice.setServiceId(resultSet.getInt("service_id"));
                serviceInvoice.setQuantity(resultSet.getInt("quantity"));
                serviceInvoice.setPrice(resultSet.getDouble("price"));
                serviceInvoice.setStatus(resultSet.getBoolean("status"));

                serviceInvoices.add(serviceInvoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return serviceInvoices;
    }

    public boolean updateServiceInvoice(ServiceInvoice serviceInvoice) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL updateServiceInvoice(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        ) {
            callableStatement.setInt(1, serviceInvoice.getId());
            callableStatement.setString(2, serviceInvoice.getName());
            callableStatement.setString(3, serviceInvoice.getStaffId());
            callableStatement.setDate(4, new java.sql.Date(serviceInvoice.getPrintingDate().getTime()));
            callableStatement.setDate(5, null);
            callableStatement.setString(6, serviceInvoice.getApartmentId());
            callableStatement.setString(7, serviceInvoice.getNotes());
            callableStatement.setInt(8, serviceInvoice.getServiceId());
            callableStatement.setInt(9, serviceInvoice.getQuantity());
            callableStatement.setDouble(10, serviceInvoice.getPrice());
            callableStatement.setBoolean(11, serviceInvoice.isStatus());

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteServiceInvoice(int serviceInvoiceId) throws SQLIntegrityConstraintViolationException {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL deleteServiceInvoice(?)}");
        ) {
            callableStatement.setInt(1, serviceInvoiceId);

            int rowsAffected = callableStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                throw new SQLIntegrityConstraintViolationException("No rows affected. Probably the service invoice with ID " + serviceInvoiceId + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException("Error while deleting Apartment. Details: " + e.getMessage());
        }
    }
    
    public ServiceInvoice getServiceInvoiceById(int serviceInvoiceId) {
    	ServiceInvoice serviceInvoice = null;
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL getServiceInvoiceById(?)}");
        	
        ) {
            callableStatement.setInt(1, serviceInvoiceId);
            try (ResultSet resultSet = callableStatement.executeQuery()) {
                if (resultSet.next()) {
                	
                	serviceInvoice = new ServiceInvoice();
                	
                	serviceInvoice.setId(resultSet.getInt("id"));
                    serviceInvoice.setName(resultSet.getString("name"));
                    serviceInvoice.setStaffId(resultSet.getString("staff_id"));
                    serviceInvoice.setPrintingDate(resultSet.getDate("printing_date"));
                    serviceInvoice.setPaymentDate(resultSet.getDate("payment_date"));
                    serviceInvoice.setApartmentId(resultSet.getString("apartment_id"));
                    serviceInvoice.setNotes(resultSet.getString("notes"));
                    serviceInvoice.setServiceId(resultSet.getInt("service_id"));
                    serviceInvoice.setQuantity(resultSet.getInt("quantity"));
                    serviceInvoice.setPrice(resultSet.getDouble("price"));
                    serviceInvoice.setStatus(resultSet.getBoolean("status"));

                }
            }
        } catch (SQLException e) {
        	System.out.println(2);
            e.printStackTrace();
        }

        return serviceInvoice;
    }
    
    public boolean setStatusToTrue(int serviceInvoiceId, Date paymentDate) {
        try (
            Connection connection = DBCon.getConn();
            CallableStatement callableStatement = connection.prepareCall("{CALL setStatusToTrue(?, ?)}");
        ) {
            callableStatement.setInt(1, serviceInvoiceId);
            callableStatement.setDate(2, paymentDate);

            int rowsAffected = callableStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
	public List<ServiceInvoice> selectServiceInvoice(int pageNumber, int rowsOfPage, int serviceId, String apartmentId, String staffId, Date fromDate, Date toDate) {
			
			List<ServiceInvoice> serviceInvoices = new ArrayList<>();
			try (
					var conn = DBCon.getConn();
					CallableStatement cs = conn.prepareCall("{call getServiceInvoice(?,?,?,?,?,?,?)}");			
				) 
			{
				cs.setInt(1, pageNumber);
		        cs.setInt(2, rowsOfPage);
		        cs.setInt(3, serviceId);
		        cs.setString(4, apartmentId);
		        cs.setString(5, staffId);
		        cs.setDate(6, fromDate);
		        cs.setDate(7, toDate);
				ResultSet resultSet = cs.executeQuery();
				while (resultSet.next()) {
					ServiceInvoice serviceInvoice = new ServiceInvoice();
					
					serviceInvoice.setId(resultSet.getInt("id"));
	                serviceInvoice.setName(resultSet.getString("name"));
	                serviceInvoice.setStaffId(resultSet.getString("staff_id"));
	                serviceInvoice.setPrintingDate(resultSet.getDate("printing_date"));
	                serviceInvoice.setPaymentDate(resultSet.getDate("payment_date"));
	                serviceInvoice.setApartmentId(resultSet.getString("apartment_id"));
	                serviceInvoice.setNotes(resultSet.getString("notes"));
	                serviceInvoice.setServiceId(resultSet.getInt("service_id"));
	                serviceInvoice.setQuantity(resultSet.getInt("quantity"));
	                serviceInvoice.setPrice(resultSet.getDouble("price"));
	                serviceInvoice.setStatus(resultSet.getBoolean("status"));

	                serviceInvoices.add(serviceInvoice);			
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return serviceInvoices;
		}
	
	public int countServiceInvoice(int serviceId, String apartmentId, String staffId, Date fromDate, Date toDate) {
		int count = 0;
		try(
				var conn = DBCon.getConn();
				var cs = conn.prepareCall("{call countInvoice(?,?,?,?,?)}");
			) {
		        cs.setInt(1, serviceId);
		        cs.setString(2, apartmentId);
		        cs.setString(3, staffId);
		        cs.setDate(4, fromDate);
		        cs.setDate(5, toDate);
		        
		        var rs = cs.executeQuery();
				while(rs.next()) {
					count = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public List<ServiceInvoice> getServiceInvoicesByApartmentId(String apartmentId) {
	    List<ServiceInvoice> serviceInvoices = new ArrayList<>();

	    try (
	        Connection connection = DBCon.getConn();
	        CallableStatement callableStatement = connection.prepareCall("{CALL getServiceInvoicesByApartmentId(?)}");
	    ) {
	        callableStatement.setString(1, apartmentId);
	        ResultSet resultSet = callableStatement.executeQuery();

	        while (resultSet.next()) {
	            ServiceInvoice serviceInvoice = new ServiceInvoice();

	            serviceInvoice.setId(resultSet.getInt("id"));
	            serviceInvoice.setName(resultSet.getString("name"));
	            serviceInvoice.setPrintingDate(resultSet.getDate("printing_date"));
	            serviceInvoice.setPaymentDate(resultSet.getDate("payment_date"));
	            serviceInvoice.setNotes(resultSet.getString("notes"));
	            serviceInvoice.setServiceId(resultSet.getInt("service_id"));
	            serviceInvoice.setQuantity(resultSet.getInt("quantity"));
	            serviceInvoice.setPrice(resultSet.getDouble("price"));
	            serviceInvoice.setStatus(resultSet.getBoolean("status"));

	            serviceInvoices.add(serviceInvoice);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return serviceInvoices;
	}


}
